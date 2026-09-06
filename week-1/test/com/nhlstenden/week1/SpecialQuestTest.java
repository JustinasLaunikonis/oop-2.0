package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialQuestTest
{
    private SpecialQuest specialQuest;
    private Item item;

    @BeforeEach
    void setup()
    {
        this.specialQuest = new SpecialQuest("Raid the crypt", 5, 80, new Mage("Ghoul", 40, 10, 2));
        this.item = new Item("Silver Sword");
    }

    @Test
    void constructor_withValidValues_startsWithoutItems()
    {
        assertTrue(this.specialQuest.getItems().isEmpty());
    }

    @Test
    void addItem_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.specialQuest.addItem(null));
    }

    @Test
    void addItem_withItem_addsItemToItems()
    {
        this.specialQuest.addItem(this.item);
        assertTrue(this.specialQuest.getItems().contains(this.item));
    }

    @Test
    void requiredExperiencePoints_withDifficultyFive_returnsForty()
    {
        assertEquals(40, this.specialQuest.requiredExperiencePoints());
    }
}
