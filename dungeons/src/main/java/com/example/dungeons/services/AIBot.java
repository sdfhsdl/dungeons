package com.example.dungeons.services;

import com.example.dungeons.controllers.Response;
import com.example.dungeons.models.fighter.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy
public class AIBot {
    private int defendPoint = 0;
    private int attackPoint = 0;
    private int skipPoint = 0;
    private Fighter activeFighter;
    private Fighter userFighter;
    private FightActions fightActions;
    public void perform(){
        String value = getValueBasedOnProbability();
        Response response = new Response();
        response.setArg(value);
        if(value.equals(FightService.ATTACK_POINT)){
            response.setId(userFighter.getName());
        }else{
            response.setId(FightService.TARGET_YOURSELF);
        }
        fightActions.getAction(response, activeFighter);
    }
    @Autowired
    public void setUserFighter(Fighter userFighter){
        this.userFighter = userFighter;
    }
    @Autowired
    public void setFightActions(FightActions fightActions){this.fightActions = fightActions;}
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
        return point;
    }
    private int getPointByAttackBot(){
        int point = 10;
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
            return FightService.DEFEND_POINT;
        }
        rand -= attackPoint;
        if(rand < 0){
            return FightService.ATTACK_POINT;
        }
            return FightService.SKIP_POINT;
    }
}
