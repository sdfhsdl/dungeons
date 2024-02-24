package com.example.dungeons.models.fighter.characteristics;

public class Human extends Race{
    private final String RaceName = "Человек";
    private final int REGENERATION = 1;
    private int count = 0;
    private int health = 80;
    private int attack = 9;
    private int armor = 4;
    private int speed = 5;
    @Override
    public void action() {
        if(count == 4){
            uniqueSkill(REGENERATION);
            count = 0;
        }
        count++;
    }
    public String getRaceName() {
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
        setHealth(getHealth() + param);
    }
}
