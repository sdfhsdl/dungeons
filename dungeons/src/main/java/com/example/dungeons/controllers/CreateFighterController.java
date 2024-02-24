package com.example.dungeons.controllers;

import com.example.dungeons.services.FighterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateFighterController {
    private  final FighterService fighterService;

    public CreateFighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("RaceTypes", fighterService.getFighterListType());
        return "createFighter.html";
    }
    @PostMapping("/")
    public String createFighter(@RequestParam String namePerson, @RequestParam String raceType){
        fighterService.createFighter(namePerson, fighterService.getRace(raceType));
        return "redirect:/fighterPage";
    }
}
