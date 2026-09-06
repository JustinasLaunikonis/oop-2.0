package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest
{
    private Warrior warrior;
    private Character enemy;

    @BeforeEach
    void setup()
    {
        this.warrior = new Warrior("Aldric", 100, 20, 5);
        this.enemy = new Mage("Shade", 60, 12, 2);
    }

    @Test
    void useSpecialAbility_once_increasesAttackPowerByTen()
    {
        this.warrior.useSpecialAbility();
        assertEquals(30, this.warrior.getAttackPower());
    }

    @Test
    void useSpecialAbility_twice_doesNotStackBoost()
    {
        this.warrior.useSpecialAbility();
        this.warrior.useSpecialAbility();
        assertEquals(30, this.warrior.getAttackPower());
    }

    @Test
    void useSpecialAbility_thenAttack_dealsBoostedDamage()
    {
        this.warrior.useSpecialAbility();
        this.warrior.attack(this.enemy);
        assertEquals(32, this.enemy.getHealthPoints());
    }

    @Test
    void resetSpecialAbility_afterUse_restoresOriginalAttackPower()
    {
        this.warrior.useSpecialAbility();
        this.warrior.resetSpecialAbility();
        assertEquals(20, this.warrior.getAttackPower());
    }

    @Test
    void resetSpecialAbility_withoutUse_keepsAttackPower()
    {
        this.warrior.resetSpecialAbility();
        assertEquals(20, this.warrior.getAttackPower());
    }
}
