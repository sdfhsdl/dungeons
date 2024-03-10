package com.example.dungeons.controllers;

import com.example.dungeons.services.DungeonService;
import com.example.dungeons.services.FightService;
import com.example.dungeons.services.FighterService;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LevelController {
    private final DungeonService dungeonService;
    private final FighterService fighterService;
    private final FightService fightService;
    private final GeneratorHTML generatorHTML;
    public LevelController(DungeonService dungeonService, FighterService fighterService, FightService fightService, GeneratorHTML generatorHTML) {
        this.dungeonService = dungeonService;
        this.fighterService = fighterService;
        this.fightService = fightService;
        this.generatorHTML = generatorHTML;
    }
    @GetMapping("/level")
    public String getLevel(Model model){
        fightService.init(fighterService.getActiveFighter());
        model.addAttribute("userFighter", fighterService.getActiveFighter());
        model.addAttribute("activeFighter", fightService.getActiveFighter());
        model.addAttribute("enemies", dungeonService.getActiveLevel().getEnemies());
        return "level.html";
    }
    @PostMapping("/update_fightersQueue-url")
    @ResponseBody
    public String updateFightersQueue(@RequestBody String body) {
        System.out.println("Update queue");
        fightService.getAction(body);
        fightService.nextMove();
        return generatorHTML.getHTMLAllFightersBlock(dungeonService.getActiveLevel().getEnemies()) +
                "<div> Test success<div>";
    }
    @PostMapping("/update_activeFighter-url")
    @ResponseBody
    public String updateFight(){
        System.out.println("Update fight");
        return generatorHTML.getHTMLActiveFighterBlock(fightService.getActiveFighter());
    }
    @PostMapping("/update_user_url")
    @ResponseBody
    public String updateUser(){
        return generatorHTML.getHTMLUserBlock(fightService.getUserFighter());
    }
}
