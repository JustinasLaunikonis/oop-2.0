package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest
{
    private Mage mage;

    @BeforeEach
    void setup()
    {
        this.mage = new Mage("Shade", 60, 12, 2);
    }

    @Test
    void useSpecialAbility_once_increasesDefencePowerByTen()
    {
        this.mage.useSpecialAbility();
        assertEquals(12, this.mage.getDefencePower());
    }

    @Test
    void useSpecialAbility_twice_doesNotStackBoost()
    {
        this.mage.useSpecialAbility();
        this.mage.useSpecialAbility();
        assertEquals(12, this.mage.getDefencePower());
    }

    @Test
    void useSpecialAbility_thenDefend_takesLessDamage()
    {
        this.mage.useSpecialAbility();
        this.mage.defend(20);
        assertEquals(52, this.mage.getHealthPoints());
    }

    @Test
    void resetSpecialAbility_afterUse_restoresOriginalDefencePower()
    {
        this.mage.useSpecialAbility();
        this.mage.resetSpecialAbility();
        assertEquals(2, this.mage.getDefencePower());
    }

    @Test
    void resetSpecialAbility_withoutUse_keepsDefencePower()
    {
        this.mage.resetSpecialAbility();
        assertEquals(2, this.mage.getDefencePower());
    }
}
