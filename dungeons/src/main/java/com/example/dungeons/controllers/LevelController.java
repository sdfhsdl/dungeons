package com.example.dungeons.controllers;

import com.example.dungeons.services.DungeonService;
import com.example.dungeons.services.FightService;
import com.example.dungeons.services.FighterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LevelController {
    private final DungeonService dungeonService;
    private final FighterService fighterService;
    private final FightService fightService;
    public LevelController(DungeonService dungeonService, FighterService fighterService, FightService fightService) {
        this.dungeonService = dungeonService;
        this.fighterService = fighterService;
        this.fightService = fightService;
    }
    @GetMapping("/level")
    public String getLevel(Model model){
        fightService.init(fighterService.getActiveFighter());
        model.addAttribute("userFighter", fighterService.getActiveFighter());
        model.addAttribute("fightService", fightService);
        model.addAttribute("activeLevel", dungeonService.getActiveLevel());
        model.addAttribute("enemies", dungeonService.getActiveLevel().getEnemies());
        return "level.html";
    }
    @PostMapping("/level")
        public String courseOfTheFight(){
            return "";
        }
}
