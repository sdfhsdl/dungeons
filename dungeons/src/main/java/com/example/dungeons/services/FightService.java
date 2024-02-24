package com.example.dungeons.services;

import com.example.dungeons.models.fighter.Fighter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
@SessionScope
public class FightService {
    private final Fighter userFighter;
    private Fighter activeFighter;
    private List<Fighter> fighters;
    private final DungeonService dungeonService;
    private Queue<Fighter> fighterQueue = new PriorityQueue();
    private AIBot aiBot;
    private boolean userMove;
    public FightService(Fighter userFighter, DungeonService dungeonService) {
        this.userFighter = userFighter;
        this.dungeonService = dungeonService;
        fighters = dungeonService.getActiveLevel().getEnemies();
        fighters.add(userFighter);
        aiBot = new AIBot(this, userFighter);
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
    public void nextMove(){
        activeFighter = fighterQueue.poll();
        if(activeFighter == null){
            updateInitiative();
            fillingTheQueue();
            activeFighter = fighterQueue.poll();
        }
        AIMove();
    }
    public boolean getUserMove(){
        return userMove;
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
        if (!activeFighter.getIsUser()){
            aiBot.setActiveFighter(activeFighter);
            aiBot.perform();
        }
    }

}
