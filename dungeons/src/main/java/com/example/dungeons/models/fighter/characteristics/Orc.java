package com.example.dungeons.models.fighter.characteristics;

public class Orc extends Race{
    private final String RaceName = "Орк";
    private int countAction = 0;
    private final int VALUE_FOR_PERFORMING_UNIQUE_SKILL= 9;
    private final int VALUE_UP_ATTACK = 1;
    private int count = 0;
    private int health = 150;
    private int attack = 10;
    private int armor = 11;
    private int speed = 4;
    @Override
    public void action() {
        if(countAction == 1){
            uniqueSkill(-VALUE_UP_ATTACK);
        }
        if(countAction == VALUE_FOR_PERFORMING_UNIQUE_SKILL){
            uniqueSkill(VALUE_UP_ATTACK);
            countAction = 0;
        }else{
            countAction++;
        }
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
        setAttack(getAttack() + param);
    }
}
