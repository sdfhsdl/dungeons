package com.example.dungeons.services;

import com.example.dungeons.models.fighter.Fighter;

public class AIBot {
    private final String DEFEND_POINT = "defend";
    private final String ATTACK_POINT = "attack";
    private final String SKIP_POINT = "skip";
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
        if(value.equals(ATTACK_POINT)){
            fightService.attack(userFighter);
        }
        if(value.equals(DEFEND_POINT)){
            fightService.defendYourself();
        }
        if(value.equals(SKIP_POINT)){
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
        point += activeFighter.getInitiative()/activeFighter.getPriceAction();
        skipPoint = point;
    }
    private void setDefendPoint(){
        int point = 0;
        point -= getPointByAttackBot();
        point -= getPointByHealthUser();
        point += getPointByArmorBot();
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
            return DEFEND_POINT;
        }
        rand -= attackPoint;
        if(rand < 0){
            return ATTACK_POINT;
        }
            return SKIP_POINT;
    }
}
