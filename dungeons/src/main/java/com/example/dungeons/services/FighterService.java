package com.example.dungeons.services;

import com.example.dungeons.models.fighter.Fighter;
import com.example.dungeons.models.fighter.RaceType;
import com.example.dungeons.models.fighter.characteristics.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dungeons.models.fighter.RaceType.*;

@Service
@Configuration
@SessionScope
public class FighterService {
    private Fighter activeFighter;
    public List<String> getFighterListType(){
        List<String> listFighter = new ArrayList<>();
        RaceType[] raceTypes = RaceType.values();
        for(int i = 0; i < raceTypes.length; i++){
            listFighter.add(raceTypes[i].getTitle());
        }
        return listFighter;
    }
    @Bean
    @Lazy
    public Fighter createFighter(String name, Race raceType){
        Fighter fighter = new Fighter(raceType, true);
        fighter.setName(name);
        activeFighter = fighter;
        System.out.println(fighter);
        return fighter;
    }
    public Fighter getActiveFighter(){
        return activeFighter;
    }
    public Race getRace(String title){
        return getMapWithRaces().get(title);
    }
    private Map<String, Race> getMapWithRaces(){
        Map<String, Race> races = new HashMap<>();
        races.put(DWARF.getTitle(), new Dwarf());
        races.put(ORK.getTitle(), new Orc());
        races.put(ELF.getTitle(), new Elf());
        races.put(HUMAN.getTitle(), new Human());
        return races;
    }
}
