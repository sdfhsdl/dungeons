package com.example.dungeons.models.dungeon.randomGenerator;

import com.example.dungeons.models.fighter.Fighter;
import com.example.dungeons.models.dungeon.Dungeon;
import com.example.dungeons.models.dungeon.Level;
import com.example.dungeons.models.fighter.characteristics.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Generator {
    private final int MAX_EXP_FOR_BASE_MOB = 2000;
    public Dungeon dungeonBuilder(double difficult_coefficient){
        Dungeon dungeon = new Dungeon();
        dungeon.setDungeonName(locationNameGenerator());
        List<Level> levels = levelsBuilder(difficult_coefficient);
        for(int i = 0; i < levels.size(); i++){
            Level level = levels.get(i);
            level.setDifficultLevel(difficultLevel(level));
        }
        dungeon.setDungeonLevels(levels);
        return  dungeon;
    }
    private List<Level> levelsBuilder(double difficult_coefficient){
        List<Level> levels = new ArrayList<>();
        int count = randomNumber(4, difficult_coefficient) + 1;
        for(int i = 0; i < count; i++){
            levels.add(levelBuilder(difficult_coefficient));
        }
        return levels;
    }
    private Level levelBuilder(double difficult_coefficient){
        Level level = new Level(levelIdGenerator());
        level.setEnemies(enemiesBuilder(difficult_coefficient));
        return level;
    }
    public List<Fighter> enemiesBuilder(double difficult_coefficient){
        List<Fighter> enemies = new ArrayList<>();
        int count = randomNumber(6, difficult_coefficient);
        for(int i = 0; i < count; i++){
            enemies.add(enemyCreator());
        }
        return enemies;
    }
    public Fighter enemyCreator(){
        Fighter enemy = new Fighter(getRandomRace(), false);
        enemy.setName(enemyNameGenerator());
        enemy.addExp(randomNumber(MAX_EXP_FOR_BASE_MOB));
        randomUpdateLevel(enemy);
        return enemy;
    }
    private int randomNumber(int max, double difficult_coefficient){
        return (int)((Math.random() * max  + 1) * difficult_coefficient);
    }
    private int randomNumber(int max){
        return (int)((Math.random() * max));
    }
    private double difficultLevel(Level level){
        double difficultPoint = 0;
        List<Fighter> enemies = level.getEnemies();
        for(int i = 0; i < enemies.size(); i++){
            Fighter enemy = enemies.get(i);
            difficultPoint = difficultPoint + enemy.getArmor() + enemy.getAttack() + enemy.getHealth() + enemy.getSpeed();
        }
        return difficultPoint;
    }
    public Race getRandomRace(){
        List<Race> raceList = new ArrayList<>();
        raceList.add(new Human());
        raceList.add(new Elf());
        raceList.add(new Dwarf());
        raceList.add(new Orc());
        return raceList.get(randomNumber(4));
    }
    private void randomUpdateLevel(Fighter enemy){
        int points = enemy.getPointForUp();
        for(int i = 0; i < points; i++){
            int randomNumber = randomNumber(4);
            if(randomNumber == 0){
                enemy.updateLevel(enemy.ARMOR);
            }if(randomNumber == 1){
                enemy.updateLevel(enemy.ATTACK);
            }if (randomNumber == 2){
                enemy.updateLevel(enemy.SPEED);
            }if(randomNumber == 3){
                enemy.updateLevel(enemy.HEALTH);
            }
        }
    }
    private String enemyNameGenerator(){
        return "enemy: " + Math.random() * 10;
    }
    private String levelIdGenerator(){
        return String.valueOf(Math.random()) + "/" + String.valueOf(Math.random());
    }
    private String locationNameGenerator(){
        return "dungeon : " + Math.random() * 10;
    }
}
