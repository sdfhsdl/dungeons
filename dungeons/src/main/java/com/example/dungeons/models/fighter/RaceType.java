package com.example.dungeons.models.fighter;

import com.example.dungeons.models.fighter.characteristics.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum RaceType {
    DWARF ("Гном"),
    ORK("Орк"),
    ELF("Эльф"),
    HUMAN("Человек");
    private String title;
    RaceType(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
