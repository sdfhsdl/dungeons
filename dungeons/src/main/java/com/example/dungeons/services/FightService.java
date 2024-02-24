package com.example.dungeons.services;

import com.example.dungeons.models.fighter.Fighter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
@Service
@SessionScope
public class FightService {
    private Fighter userFighter;
    private Fighter activeFighter;
    private List<Fighter> fighters;
    private final DungeonService dungeonService;
    private Queue<Fighter> fighterQueue = new PriorityQueue();
    private AIBot aiBot;
    public FightService(DungeonService dungeonService) {
        this.dungeonService = dungeonService;
        fighters = dungeonService.getActiveLevel().getEnemies();
    }
    public void init(Fighter userFighter){
        this.userFighter = userFighter;
        fighters.add(userFighter);
        aiBot = new AIBot(this, userFighter);
        nextMove();
    }
    public void attack(Fighter fighter){
        fighter.setDamage(activeFighter.getAttack());
        activeFighter.performAction();
        nextMove();
    }
    public void defendYourself(){
        activeFighter.defend();
        activeFighter.performAction();
        nextMove();
    }
    public void skip(){
        for(int i = 0; i < 2; i++) {
            activeFighter.updateInitiative();
        }
        nextMove();
    }
    public Fighter getActiveFighter(){
        return activeFighter;
    }
    public Fighter getUserFighter(){
        return userFighter;
    }
    private void nextMove(){
        activeFighter = fighterQueue.poll();
        System.out.println(activeFighter);
        if(activeFighter == null){
            updateInitiative();
            fillingTheQueue();
            activeFighter = fighterQueue.poll();
        }
        if(!activeFighter.getIsUser()) {
            AIMove();
        }
    }
    private void updateInitiative(){
        for(int i = 0; i < fighters.size(); i++){
            fighters.get(i).updateInitiative();
        }
    }
    private void fillingTheQueue(){
        fighterQueue.addAll(fighters);
    }
    private void AIMove(){
            aiBot.setActiveFighter(activeFighter);
            aiBot.perform();
        }
}
