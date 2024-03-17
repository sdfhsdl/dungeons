package com.example.dungeons.services;

import com.example.dungeons.controllers.Response;
import com.example.dungeons.models.fighter.Fighter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Service
@SessionScope
public class FightService {
    public final static String DEFEND_POINT = "defend";
    public final static String ATTACK_POINT = "attack";
    public final static String SKIP_POINT = "skip";
    public final static String TARGET_YOURSELF = "yourself";
    private Fighter userFighter;
    private Fighter activeFighter;
    private List<Fighter> isDeath = new ArrayList<>();
    private List<Fighter> fighters;
    private final DungeonService dungeonService;
    private final FightActions fightActions;
    private int countFighters;
    private AIBot aiBot;
    private boolean gameOver = false;
    public FightService(DungeonService dungeonService, Fighter userFighter, FightActions fightActions) {
        this.dungeonService = dungeonService;
        this.userFighter = userFighter;
        this.fightActions = fightActions;
        init();
    }
    private void init(){
        countFighters = 0;
        fighters = dungeonService.getActiveLevel().getEnemies();
        fighters.add(userFighter);
        fighters.sort(null);
        activeFighter = fighters.get(0);
        fightActions.setFighters(fighters);
    }
    @Autowired
    public void setAiBot(AIBot aiBot){
        this.aiBot = aiBot;
    }
    public void action(String body){
        System.out.println("UserFighter -   " + userFighter.getName());
        Gson gson = new Gson();
        Response response = gson.fromJson(body, Response.class);
        System.out.println(response.getArg() + " " + response.getId()+ " " + userFighter.getName());
        fightActions.getAction(response, activeFighter);
    }
    public void nextMove(){
        updateAll();
        System.out.println("ACTIVE FIGHTER :  " + activeFighter.getName());
        if(!activeFighter.getIsUser()) {
            AIMove();
        }
    }
    public List<Fighter> getFighters(){
        return fighters;
    }
    private void updateAll(){
        activeFighter = fighters.get(countFighters);
        checkUserFighter();
        checkFightersIsAlive();
        if(countFighters < fighters.size() - 1){
            countFighters++;
        }else{
            countFighters = 0;
            fighters.sort(null);
            updateInitiative();
        }
    }
    private void updateInitiative(){
        for(int i = 0; i < fighters.size(); i++){
            fighters.get(i).updateInitiative();
        }
    }
    private void checkFightersIsAlive(){
        for(Fighter fighter : fighters){
            if(!fighter.getIsAlive()){
                isDeath.add(fighter);
            }
        }
    }
    private void checkUserFighter(){
        gameOver = !userFighter.getIsAlive();
    }
    private void AIMove(){
            aiBot.setActiveFighter(activeFighter);
            aiBot.perform();
        }
    public Fighter getActiveFighter(){
        return activeFighter;
    }
    public Fighter getUserFighter(){
        return userFighter;
    }
}
