package com.example.dungeons.controllers;

import com.example.dungeons.models.fighter.Fighter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class GeneratorHTML {
    public String getHTMLAllFightersBlock(List<Fighter> fighters){
        StringBuilder html = new StringBuilder();
        html.append("<div id=\"enemyBlocks\" class=\"enemyBlock\">" +
                "<tr>");
        for(int i = 0; i < fighters.size(); i++){
            html.append(getHTMLEnemyFighterBlock(fighters.get(i)));
        }
        html.append(
                "</tr>" +
                "</div>");
        return html.toString();
    }
    public String getHTMLEnemyFighterBlock(Fighter fighter){
        return getHTMLFighterBlock(fighter, "class = \"enemies\"");
    }
    public String getHTMLActiveFighterBlock(Fighter fighter){
        if(!fighter.getIsUser()) {
            return getHTMLFighterBlock(fighter, "id = \"activeFighter\" class = \"userBlock\"");
        }else{
            return "<div id=\"activeFighter\" class = \"userBlock\"></div>";
        }
    }
    public String getHTMLUserBlock(Fighter fighter){
        return "<div id=\"userFighter\" class=\"userBlock\">" +
                getHTMLPatternFighterBlock(fighter)
                + "<button id=\"attack_button\" class=\"button\">Атака</button>\n" +
                "<button id=\"defend_button\" class=\"button\">Оборона</button>\n" +
                "<button id=\"skip_button\" class=\"button\">Пропустить</button>\n" +
                "</div>"
                ;
    }
    public String getHTMLLogBlock(String log){
        return "<div id=\"logBlock\" class=\"loggingFightBlock\">" +
                log +
                "</div>";
    }
    private String getHTMLFighterBlock(Fighter fighter, String nameBlock){
        String html =
                "<div " + nameBlock + ">" +
                        getHTMLPatternFighterBlock(fighter) +
                        "</div>" +
                        "</br>";
        return html;
    }
    private String getHTMLPatternFighterBlock(Fighter fighter){
        return "<p>" +
                "<span class=\"nameFighter\">" + fighter.getName()  +
                "</span>" +
                "</p>" +
                "<p>" +
                "<span>Здоровье: </span>" +
                "<span> " + fighter.getHealth() + "</span>" +
                "<span> / </span>" +
                "<span> " + fighter.getFullHealth() + "</span>" +
                "</p>" +
                "<p>" +
                "<span>Атака: </span>" +
                "<span>" + fighter.getAttack() + "</span>" +
                "</p>" +
                "<p>" +
                "<span>Броня: </span>" +
                "<span>" + fighter.getArmor() + "</span>" +
                "</p>" +
                "<p>" +
                "<span>Скорость: </span>" +
                "<span> " + fighter.getSpeed() + "</span>" +
                "</p>" +
                "<p>" +
                "<span>Инициатива: </span>" +
                "<span>" + fighter.getInitiative() +  "</span>" +
                "</p>";
    }
    public String gameOver(){
        return "<div class=\"dieBlock\"> You died </div>";
    }
}
