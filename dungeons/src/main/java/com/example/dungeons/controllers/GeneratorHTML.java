package com.example.dungeons.controllers;

import com.example.dungeons.models.fighter.Fighter;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class GeneratorHTML {
    public String getHTMLAllFightersBlock(List<Fighter> fighters){
        StringBuilder html = new StringBuilder();
        html.append("<div id=\"enemyBlocks\" class=\"enemyBlock\">\n" +
                "            <tr>\n");
        for(int i = 0; i < fighters.size(); i++){
            html.append(getHTMLFighterBlock(fighters.get(i)));
        }
        html.append(
                "            </tr>\n" +
                "        </div>");
        return html.toString();
    }
    public String getHTMLFighterBlock(Fighter fighter){
        String html =
                        "                <div class=\"enemies\">" +
                                "<p>\n" +
                                "                        <span class=\"nameFighter\">" + fighter.getName() + " \n" +
                                "                        </span>\n" +
                                "                    </p>" +
                "                    <p>\n" +
                "                        <span>Здоровье: </span>\n" +
                "                        <span> " + fighter.getHealth() + "</span>\n" +
                "                        <span> / </span>\n" +
                "                        <span> " + fighter.getFullHealth() + "</span>\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        <span>Атака: </span>\n" +
                "                        <span>" + fighter.getAttack() + "</span>\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        <span>Броня: </span>\n" +
                "                        <span>" + fighter.getArmor() + "</span>\n" +
                "                    </p>\n" +
                "                    <p>\n" +
                "                        <span>Скорость: </span>\n" +
                "                        <span> " + fighter.getSpeed() + "</span>\n" +
                "                    </p>\n" +
        "</div>\n" +
        "                </br>\n";
        return html;
    }
}
