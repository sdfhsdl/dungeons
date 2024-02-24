package com.example.dungeons.models.fighter.characteristics;

import java.util.HashMap;
import java.util.Map;

public class LevelModifiers {
    private int exp = 0;
    private int currentLevel = 0;
    private final int MAX_LEVEL = 18;
    private final int FIRST_TIER = 6;
    private final int STEP_OF_THE_FIRST_TIER = 4;
    private final int SECOND_TIER = 12;
    private final int STEP_OF_THE_SECOND_TIER = 2;
    private final int THIRD_TIER = 18;
    private final int STEP_OF_THE_THIRD_TIER = 1;
    private int pointForUp = 0;
    private Map<Integer, Integer> levels = new HashMap<>();
    public LevelModifiers(){
        generateLevelsMap();
    }
    public int getExp() {
        return exp;
    }
    public int getCurrentLevel(){
        return currentLevel;
    }
    public void addExp(int exp){
        this.exp += exp;
        while (checkNewLevel()){
            levelUp();
        }
    }
    public int getPointForUp(){
        return pointForUp;
    }
    public void usePointForUp(){
        pointForUp -= 1;
    }
    public int getStepOfLevelingUpForOnePoint(){
        if(currentLevel > 0 & currentLevel < FIRST_TIER){
            return STEP_OF_THE_FIRST_TIER;
        }if(currentLevel > FIRST_TIER  & currentLevel < SECOND_TIER){
            return STEP_OF_THE_SECOND_TIER;
        }else{
            return STEP_OF_THE_THIRD_TIER;
        }
    }
    public int getExpNextLevel(){
        return levels.get(currentLevel + 1);
    }
    public int getExpForNextLevel(){
        return levels.get(currentLevel + 1) - exp;
    }
    private boolean checkNewLevel(){
        if(exp >= levels.get(currentLevel + 1)){
            return true;
        }
        return false;
    }
    private void levelUp(){
        currentLevel++;
        pointForUp += 2;
    }
    private void generateLevelsMap(){
        for(int i = 1; i < MAX_LEVEL; i++){
            levels.put(i, expForNewLevel(i));
        }
    }
    private int expForNewLevel(int level){
        int expForNewLevel = (level * 100) + (int)((level * 100)/1.5);
        return expForNewLevel;
    }
}
