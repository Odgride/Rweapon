package xyz.raitaki.rweapons.weapon.weapons;

import org.bukkit.Material;
import org.joml.Vector3f;
import xyz.raitaki.rweapons.weapon.SkillClickType;
import xyz.raitaki.rweapons.weapon.Weapon;
import xyz.raitaki.rweapons.weapon.skills.ConsoleSkill;
import xyz.raitaki.rweapons.weapon.skills.ThrowSkill;

import java.util.UUID;

public class Allah1 extends Weapon {

    public Allah1(UUID owner) {
        super("ALLAHVERDİDEMİCEN", owner, 1, Material.IRON_BLOCK, true);
        addSkill(new ThrowSkill(this, new Vector3f(0.5f,0.5f,0.5f), 0.12), SkillClickType.LEFT_CLICK);
        addSkill(new ConsoleSkill(this, new Vector3f(0.5f, 0.5f, 0.5f), 0.12), SkillClickType.SHIFT_RIGHT_CLICK);
        //createHeadItem();
    }
}
