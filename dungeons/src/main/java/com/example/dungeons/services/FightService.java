package com.example.dungeons.services;

import com.example.dungeons.controllers.Response;
import com.example.dungeons.models.fighter.Fighter;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
@Service
@SessionScope
public class FightService {
    public final static String DEFEND_POINT = "defend";
    public final static String ATTACK_POINT = "attack";
    public final static String SKIP_POINT = "skip";
    private Fighter userFighter;
    private Fighter activeFighter;
    private List<Fighter> fighters;
    private final DungeonService dungeonService;
    private Queue<Fighter> fighterQueue = new PriorityQueue();
    private AIBot aiBot;
    public void getAction(String action){
        Gson gson = new Gson();
        Response response = gson.fromJson(action, Response.class);
        if(response.getArg().equals(ATTACK_POINT)){
            attack(getFighterByName(response.getId()));
        }if(response.getArg().equals(DEFEND_POINT)){
            defendYourself();
        }if(response.getArg().equals(SKIP_POINT)){
            skip();
        }
    }
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
    }
    public void defendYourself(){
        activeFighter.defend();
        activeFighter.performAction();
    }
    public void skip(){
        for(int i = 0; i < 2; i++) {
            activeFighter.updateInitiative();
        }
    }
    public Fighter getActiveFighter(){
        return activeFighter;
    }
    public Fighter getUserFighter(){
        return userFighter;
    }
    public Queue<Fighter> getFighterQueue(){return fighterQueue;}
    public void nextMove(){
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
        private Fighter getFighterByName(String name){
        return fighters
                .stream().findFirst()
                .filter(fighter -> fighter.getName()
                        .equals(name))
                .get();
        }
}
