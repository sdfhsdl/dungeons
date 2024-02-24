package com.example.dungeons.models.dungeon;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
public class Dungeon {
    private String dungeonName;
    private List<Level> levels;

    public String getDungeonName() {
        return dungeonName;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public List<Level> getDungeonLevels() {
        return levels;
    }

    public void setDungeonLevels(List<Level> levels) {
        this.levels = levels;
    }
}
