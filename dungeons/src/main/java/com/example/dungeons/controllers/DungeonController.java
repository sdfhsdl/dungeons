package com.example.dungeons.controllers;

import com.example.dungeons.models.dungeon.Level;
import com.example.dungeons.services.DungeonService;
import com.example.dungeons.services.FighterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DungeonController {
    public DungeonController(FighterService fighterService, DungeonService dungeonService) {
        this.fighterService = fighterService;
        this.dungeonService = dungeonService;
    }
    private final FighterService fighterService;
    private final DungeonService dungeonService;
    @GetMapping("/dungeon")
    public String dungeon(Model model){
        model.addAttribute("activeFighter", fighterService.getActiveFighter());
        dungeonService.createDungeon();
        model.addAttribute("levels", dungeonService.getSortedLevelsByDifficult());
        return "dungeon.html";
    }
    @PostMapping("/dungeon")
    public String explore(@RequestParam("id") String id){
        dungeonService.setActiveLevel(id);
        return "redirect:/level";
    }
}
