package com.example.dungeons.models.fighter.characteristics;

public abstract class Race {
    private String RaceName;
    private boolean isPassiveUniqueSkill;
    private int attack;
    private int armor;
    private int speed;
    public abstract  void action();
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public String getRaceName() {
        return RaceName;
    }
    private int health;
    public int getAttack(){
        return attack;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    public int getArmor(){
        return armor;
    }
    public void setArmor(int armor){
        this.armor = armor;
    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public abstract void uniqueSkill(int param);


}
