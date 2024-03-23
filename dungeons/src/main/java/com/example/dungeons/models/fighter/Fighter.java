package com.example.dungeons.models.fighter;

import com.example.dungeons.models.fighter.characteristics.LevelModifiers;
import com.example.dungeons.models.fighter.characteristics.Race;

public class Fighter implements Comparable<Fighter>{
    public final String ARMOR = "ARMOR";
    public final String ATTACK = "ATTACK";
    public final String SPEED = "SPEED";
    public final String HEALTH = "HEALTH";
    private final int priceAction;
    private String name;
    private int armor;
    private int attack;
    private int speed;
    private int fullHealth;
    private int health;
    private int initiative;
    private boolean isAlive = true;
    private boolean isUser;
    private boolean isDefend = false;
    private LevelModifiers levelModifiers = new LevelModifiers();
    private final Race race;

    public Fighter(Race race, boolean isUser) {
        this.race = race;
        this.isUser = isUser;
        setRaceModifiers();
        setInitiative();
        priceAction = Math.round(20 / speed);
    }
    public int getInitiative(){
        return initiative;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Race getRace() {
        return race;
    }
    public int getArmor() {
        return armor;
    }
    public int getAttack() {
        return attack;
    }
    public int getSpeed() {
        return speed;
    }
    public int getHealth() {
        return health;
    }
    public int getFullHealth(){return fullHealth;}
    public int getPriceAction(){return priceAction;}
    public boolean getIsUser(){return isUser;}
    public boolean getIsAlive(){
        return isAlive;
    }
    public void setDamage(int attack){
        health -= Math.round((double)attack - ((double)attack/100 * armor));
        isDefend = false;
        if(health <= 0){
            health = 0;
            isAlive = false;
            initiative = 0;
        }
    }
    public void defend(){
        isDefend = true;
        armor += Math.round((double)armor * 1.2);
    }
    public void addExp(int exp){
        levelModifiers.addExp(exp);
    }
    public void updateLevel(String update){
        if(levelModifiers.getPointForUp() > 0){
            if(update.equals("ARMOR")){
                armor += levelModifiers.getStepOfLevelingUpForOnePoint();
            }
            if(update.equals("ATTACK")){
                attack += levelModifiers.getStepOfLevelingUpForOnePoint();
            }
            if(update.equals("SPEED")){
                speed += levelModifiers.getStepOfLevelingUpForOnePoint();
                setInitiative();
            }
            if(update.equals("HEALTH")){
                fullHealth += levelModifiers.getStepOfLevelingUpForOnePoint() * 2;
                health = fullHealth;
            }
            levelModifiers.usePointForUp();
        }
    }
    public void performAction(){
        initiative -= priceAction;
    }
    public void updateInitiative(){
        initiative = (int)Math.round(initiative + ((double)speed / 10 ));
    }
    public int getExp(){
       return levelModifiers.getExp();
    }
    public int getExpForNextLevel(){
        return levelModifiers.getExpNextLevel();
    }
    public int getLevel(){
        return levelModifiers.getCurrentLevel();
    }
    public int getPointForUp(){
        return levelModifiers.getPointForUp();
    }
    private void setRaceModifiers(){
        armor = race.getArmor();
        attack = race.getAttack();
        speed = race.getSpeed();
        fullHealth = race.getHealth();
        health = fullHealth;
    }
    private void setInitiative(){
        initiative = speed * 10;
    }
    @Override
    public String toString() {
        return "Fighter{" +
                "name='" + name + '\'' +
                ", armor=" + armor +
                ", attack=" + attack +
                ", speed=" + speed +
                ", health=" + fullHealth +
                ", initiative=" + initiative +
                ", Race=" + race.getRaceName() +
                ", Level=" + levelModifiers.getCurrentLevel() +
                '}';
    }

    @Override
    public int compareTo(Fighter fighter) {
        if (this.initiative > fighter.getInitiative()){
            return -1;
        }if(this.initiative < fighter.getInitiative()){
            return 1;
        }
        return 0;
    }
}
