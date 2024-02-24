package com.example.dungeons.models.fighter.characteristics;

public class Elf extends Race{
    private int health = 70;
    private int attack = 9;
    private int armor = 2;
    private int speed = 9;
    private final String RaceName = "Эльф";
    private int countAction = 0;
    @Override
    public void action() {
        if(countAction == 25){
            uniqueSkill(1);
            countAction = 0;
        }
        countAction++;
    }
    public String getRaceName(){
        return RaceName;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void uniqueSkill(int param) {
        setSpeed(getSpeed() + param);
    }
}
