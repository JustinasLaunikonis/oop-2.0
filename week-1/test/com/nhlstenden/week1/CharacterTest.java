package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest
{
    private Character character;
    private Character enemy;

    @BeforeEach
    void setup()
    {
        this.character = new Warrior("Aldric", 100, 20, 5);
        this.enemy = new Warrior("Goblin", 50, 8, 3);
    }

    @Test
    void attack_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.character.attack(null));
    }

    @Test
    void attack_withEnemy_reducesEnemyHealthByDamageMinusDefence()
    {
        this.character.attack(this.enemy);
        assertEquals(33, this.enemy.getHealthPoints());
    }

    @Test
    void defend_withDamageEqualToDefence_keepsHealthPoints()
    {
        this.character.defend(5);
        assertEquals(100, this.character.getHealthPoints());
    }

    @Test
    void defend_withDamageExceedingHealth_setsHealthToZero()
    {
        this.character.defend(500);
        assertEquals(0, this.character.getHealthPoints());
    }

    @Test
    void isAlive_withZeroHealth_returnsFalse()
    {
        this.character.setHealthPoints(0);
        assertFalse(this.character.isAlive());
    }
}
