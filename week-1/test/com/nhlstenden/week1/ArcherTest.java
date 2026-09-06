package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcherTest
{
    private Archer archer;
    private Character enemy;

    @BeforeEach
    void setup()
    {
        this.archer = new Archer("Lyra", 80, 15, 3);
        this.enemy = new Warrior("Goblin", 50, 8, 3);
    }

    @Test
    void countDamage_withoutSpecialAbility_returnsAttackPower()
    {
        assertEquals(15, this.archer.countDamage());
    }

    @Test
    void countDamage_withSpecialAbility_returnsDoubleAttackPower()
    {
        this.archer.useSpecialAbility();
        assertEquals(30, this.archer.countDamage());
    }

    @Test
    void useSpecialAbility_once_doesNotChangeAttackPower()
    {
        this.archer.useSpecialAbility();
        assertEquals(15, this.archer.getAttackPower());
    }

    @Test
    void useSpecialAbility_thenAttack_dealsDoubleDamage()
    {
        this.archer.useSpecialAbility();
        this.archer.attack(this.enemy);
        assertEquals(23, this.enemy.getHealthPoints());
    }

    @Test
    void resetSpecialAbility_afterUse_returnsToNormalDamage()
    {
        this.archer.useSpecialAbility();
        this.archer.resetSpecialAbility();
        assertEquals(15, this.archer.countDamage());
    }
}
