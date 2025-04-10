package xyz.raitaki.rweapons.weapon;

import lombok.Getter;

@Getter
public abstract class Skill {

    private final String name;
    private final int level;
    private final int cost;
    private final int cooldown;
    private final int duration;
    private final Weapon weapon;

    public Skill(String name, int level, int cost, int cooldown, int duration, Weapon weapon) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.cooldown = cooldown;
        this.duration = duration;
        this.weapon = weapon;
    }


    public abstract void activate();
}
