package com.example.dungeons.models.dungeon;

import com.example.dungeons.models.fighter.Fighter;

import java.util.List;

public class Level {
    private String id;
    private List<Fighter> enemies;
    private double difficultLevel;
    public Level(String id){
        this.id = id;
    }
    public List<Fighter> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<Fighter> enemies) {
        this.enemies = enemies;
    }

    public double getDifficultLevel() {
        return difficultLevel;
    }

    public String getId() {
        return id;
    }
    public void setDifficultLevel(double difficultLevel) {
        this.difficultLevel = difficultLevel;
    }
}
