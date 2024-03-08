package com.example.dungeons.services;

import com.example.dungeons.models.fighter.Fighter;

public class AIBot {
    private int defendPoint = 0;
    private int attackPoint = 0;
    private int skipPoint = 0;
    private Fighter activeFighter;
    private Fighter userFighter;
    private final FightService fightService;

    public AIBot(FightService fightService, Fighter userFighter) {
        this.fightService = fightService;
        this.userFighter = userFighter;
    }
    public void perform(){
        String value = getValueBasedOnProbability();
        System.out.println(value);
        System.out.println(fightService.SKIP_POINT + " - " + skipPoint);
        System.out.println(fightService.ATTACK_POINT + " - " + attackPoint);
        System.out.println(fightService.DEFEND_POINT + " - " + defendPoint);
        if(value.equals(fightService.ATTACK_POINT)){
            fightService.attack(userFighter);
        }
        if(value.equals(fightService.DEFEND_POINT)){
            fightService.defendYourself();
        }
        if(value.equals(fightService.SKIP_POINT)){
            fightService.skip();
        }
    }
    public void setActiveFighter(Fighter activeFighter){
        this.activeFighter = activeFighter;
        setPoints();
    }
    private void setPoints(){
        setAttackPoint();
        setDefendPoint();
        setSkipPoint();
    }
    private void setSkipPoint(){
        int point = 0;
        point -= getPointByHealthUser();
        point += (activeFighter.getInitiative()/activeFighter.getPriceAction())/10;
        skipPoint = point;
    }
    private void setDefendPoint(){
        int point = 0;
        point -= getPointByAttackBot();
        point -= getPointByHealthUser();
        point += getPointByArmorBot();
        defendPoint = point;
    }
    private void setAttackPoint(){
        int point = 0;
        point += getPointByHealthUser();
        point += getPointByAttackBot();
        attackPoint = point;
    }
    private int getPointByHealthUser(){
        int point = 0;
        int difference = userFighter.getFullHealth() - userFighter.getHealth();
        while (difference > userFighter.getHealth()){
            point -= 1;
            difference /= 1.5;
        }
        System.out.println("Point by health user : " + point);
        return point;
    }
    private int getPointByAttackBot(){
        int point = 0;
        point += activeFighter.getAttack() / 5;
        return point;
    }
    private int getPointByArmorBot(){
        int point = 0;
        point += activeFighter.getArmor() / 5;
        return point;
    }
    private String getValueBasedOnProbability(){
        int sumPoints = defendPoint + attackPoint + skipPoint;
        int rand = (int)(Math.random() * sumPoints);
        rand -= defendPoint;
        if(rand < 0){
            return fightService.DEFEND_POINT;
        }
        rand -= attackPoint;
        if(rand < 0){
            return fightService.ATTACK_POINT;
        }
            return fightService.SKIP_POINT;
    }
}
