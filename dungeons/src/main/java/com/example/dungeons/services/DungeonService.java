package com.example.dungeons.services;

import com.example.dungeons.models.dungeon.Dungeon;
import com.example.dungeons.models.dungeon.Level;
import com.example.dungeons.models.dungeon.randomGenerator.Generator;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@SessionScope
public class DungeonService {
    private final double DEFAULT = 1.0;
    private double difficultCoefficient = DEFAULT;
    private Dungeon dungeon;
    private List<Level> levels;
    private Level activeLevel;
    public DungeonService(Generator generator) {
        this.generator = generator;
    }
    private final Generator generator;
    public Dungeon createDungeon(){
        this.dungeon = generator.dungeonBuilder(difficultCoefficient);
        levels = dungeon.getDungeonLevels();
        return dungeon;
    }
    public void setDifficult (double difficultCoefficient){
        this.difficultCoefficient = difficultCoefficient;
    }
    public double getDifficultCoefficient(){
        return difficultCoefficient;
    }
    public List<Level> getLevels(){
        return dungeon.getDungeonLevels();
    }
    public List<Level> getSortedLevelsByDifficult(){
        List<Level> levels = dungeon.getDungeonLevels();
        Collections.sort(levels, Comparator.comparing(Level::getDifficultLevel));
        return levels;
    }
    public Level getLevelById(String id){
        for(int i =0; i < levels.size(); i++){
            Level level = levels.get(i);
            if(level.getId().equals(id)){
                System.out.println("Был возвращен уровень - " + level.getId());
                return level;
            }
        }
        return null;
    }
    public Level getActiveLevel(){
        System.out.println("Был возвращен активный уровень - " + activeLevel.getId());
        return activeLevel;
    }
    public void setActiveLevel(String id){
        System.out.println("Сохранение выбранного уровня - " + id);
        activeLevel = getLevelById(id);
    }
}
