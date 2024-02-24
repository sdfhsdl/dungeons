package com.example.dungeons.models.dungeon.randomGenerator;

import com.example.dungeons.models.dungeon.Level;
import com.example.dungeons.models.fighter.Fighter;
import com.example.dungeons.models.fighter.characteristics.Race;
import com.example.dungeons.models.dungeon.Dungeon;
import org.junit.jupiter.api.Test;

import java.util.List;

class GeneratorTest {
Generator generator = new Generator();
    @Test
    void enemyCreator() {
        Dungeon dungeon = generator.dungeonBuilder(1.25);
        List<Level> levels = dungeon.getDungeonLevels();
        for(int i = 0; i < levels.size(); i++){
            List<Fighter> fighters = levels.get(i).getEnemies();
            for(int j = 0; j < fighters.size(); j++){
                System.out.println(fighters.get(j));
            }
        }
    }

    @Test
    void getRandomRace() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for(int i = 0; i < 150; i++){
            Race race = generator.getRandomRace();
            if(race.getRaceName().equals("Гном")){
                a++;
            }if(race.getRaceName().equals("Эльф")){
                b++;
            }if(race.getRaceName().equals("Человек")){
                c++;
            }if(race.getRaceName().equals("Орк")){
                d++;
            }
        }
        System.out.println(a + " - Гномов создано");
        System.out.println(b + " - Эльфов создано");
        System.out.println(c + " - Человеков создано");
        System.out.println(d + " - Орков создано");
    }
}