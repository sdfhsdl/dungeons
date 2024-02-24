package com.example.dungeons.models.fighter.characteristics;

public class Dwarf extends Race {
    private int health = 100;
    private int attack = 9;
    private int armor = 15;
    private int speed = 3;

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

    private final String RaceName = "Гном";
    private boolean usedSkill = false;
    private final int REGENERATION_HEALTH = 5;

    @Override
    public void action() {
        if (getHealth() == 0 && usedSkill == false) {
            uniqueSkill(REGENERATION_HEALTH);
        }
    }

    public String getRaceName() {
        return RaceName;
    }

    @Override
    public void uniqueSkill(int param) {
        setHealth(param);
    }
}
