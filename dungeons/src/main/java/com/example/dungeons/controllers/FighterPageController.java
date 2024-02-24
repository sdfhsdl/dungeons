package com.example.dungeons.controllers;

import com.example.dungeons.services.FighterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class FighterPageController {
    private final FighterService fighterService;

    public FighterPageController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping("/fighterPage")
    public String fighterPage(Model model){
        model.addAttribute("activeFighter", fighterService.getActiveFighter());
        return "fighterPage.html";
    }
    @PostMapping("/fighterPage")
    public String toDungeon(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/dungeon";
    }
}
