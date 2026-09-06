package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest
{
    private Quest quest;
    private Character goblin;

    @BeforeEach
    void setup()
    {
        this.goblin = new Warrior("Goblin", 50, 8, 3);
        this.quest = new Quest("Clear the cellar", 3, 50, this.goblin);
    }

    @Test
    void setDifficulty_withZero_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.quest.setDifficulty(0));
    }

    @Test
    void setDifficulty_withEleven_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.quest.setDifficulty(11));
    }

    @Test
    void setDifficulty_withTen_setsDifficulty()
    {
        this.quest.setDifficulty(10);
        assertEquals(10, this.quest.getDifficulty());
    }

    @Test
    void constructor_withNullEnemy_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new Quest("Clear the cellar", 3, 50, null));
    }

    @Test
    void requiredExperiencePoints_withDifficultyThree_returnsTwenty()
    {
        assertEquals(20, this.quest.requiredExperiencePoints());
    }
}
