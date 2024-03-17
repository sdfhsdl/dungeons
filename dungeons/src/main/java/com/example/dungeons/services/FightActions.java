package com.example.dungeons.services;

import com.example.dungeons.aspects.LogFight;
import com.example.dungeons.controllers.Response;
import com.example.dungeons.models.fighter.Fighter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FightActions {
    private List<Fighter> fighters;
    public void setFighters(List<Fighter> fighters){
        this.fighters = fighters;
    }
    @LogFight
    public void getAction(Response response, Fighter activeFighter){
        if(response.getArg().equals(FightService.ATTACK_POINT)){
            attack(activeFighter, getFighterByName(response.getId()));
        }if(response.getArg().equals(FightService.DEFEND_POINT)){
            defendYourself(activeFighter);
        }if(response.getArg().equals(FightService.SKIP_POINT)){
            skip(activeFighter);
        }
    }
    public void attack(Fighter activeFighter, Fighter targetFighter){
        targetFighter.setDamage(activeFighter.getAttack());
        activeFighter.performAction();
        if(!targetFighter.getIsAlive()){
            fighters.remove(targetFighter);
        }
    }
    public void defendYourself(Fighter activeFighter){
        activeFighter.defend();
        activeFighter.performAction();
    }
    public void skip(Fighter activeFighter){
        for(int i = 0; i < 2; i++) {
            activeFighter.updateInitiative();
        }
    }
    private Fighter getFighterByName(String name){
        for(int i = 0; i < fighters.size(); i++){
            if(fighters.get(i).getName().equals(name)){
                return fighters.get(i);
            }
        }
        return null;
    }
}
