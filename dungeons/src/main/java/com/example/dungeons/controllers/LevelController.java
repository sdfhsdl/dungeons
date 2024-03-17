package com.example.dungeons.controllers;

import com.example.dungeons.services.DungeonService;
import com.example.dungeons.services.FightService;
import com.example.dungeons.services.FighterService;
import com.example.dungeons.services.LogFightService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LevelController {
    private final DungeonService dungeonService;
    private final FighterService fighterService;
    private final FightService fightService;
    private final GeneratorHTML generatorHTML;
    private final LogFightService logFightService;
    public LevelController(DungeonService dungeonService, FighterService fighterService, FightService fightService, GeneratorHTML generatorHTML, LogFightService logFightService) {
        this.dungeonService = dungeonService;
        this.fighterService = fighterService;
        this.fightService = fightService;
        this.generatorHTML = generatorHTML;
        this.logFightService = logFightService;
    }
    @GetMapping("/level")
    public String getLevel(Model model){
        model.addAttribute("userFighter", fighterService.getActiveFighter());
        model.addAttribute("activeFighter", fightService.getActiveFighter());
        model.addAttribute("enemies", dungeonService.getActiveLevel().getEnemies());
        return "level.html";
    }
    @PostMapping("/set_action")
    @ResponseBody
    public void setAction(@RequestBody String body){
        fightService.action(body);
    }
    @PostMapping("/update_fightersQueue-url")
    @ResponseBody
    public String updateFightersQueue() {
        System.out.println("Update queue");
        return generatorHTML.getHTMLAllFightersBlock(fightService.getFighters());
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
    @PostMapping("/update_logFight")
    @ResponseBody
    public String updateLogFight(){
        return generatorHTML.getHTMLLogBlock(logFightService.getAllLog());
    }
    @PostMapping("/checkUserMove")
    @ResponseBody
    public String checkUserMove(){
        return fightService.getActiveFighter().getIsUser() ? "true" : "false";
    }
    @PostMapping("/next_move")
    @ResponseBody
    public String nextMove(){
        fightService.nextMove();
        return "next";
    }
}
