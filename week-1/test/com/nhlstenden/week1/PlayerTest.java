package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    private Player player;
    private Quest easyQuest;
    private Quest hardQuest;
    private SpecialQuest specialQuest;
    private Item item;

    @BeforeEach
    void setup()
    {
        this.player = new Player("Justinas", new Warrior("Aldric", 100, 20, 5));
        this.easyQuest = new Quest("Clear the cellar", 1, 50, new Warrior("Goblin", 50, 8, 3));
        this.hardQuest = new Quest("Slay the dragon", 3, 100, new Warrior("Dragon", 300, 40, 20));
        this.specialQuest = new SpecialQuest("Raid the crypt", 1, 50, new Mage("Ghoul", 40, 10, 2));
        this.item = new Item("Silver Sword");
        this.specialQuest.addItem(this.item);
    }

    @Test
    void canPlayQuest_withTooFewExperiencePoints_returnsFalse()
    {
        assertFalse(this.player.canPlayQuest(this.hardQuest));
    }

    @Test
    void playQuest_withLockedQuest_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.player.playQuest(this.hardQuest));
    }

    @Test
    void playQuest_whenCharacterWins_addsExperiencePointsReward()
    {
        this.player.playQuest(this.easyQuest);
        assertEquals(50, this.player.getExperiencePoints());
    }

    @Test
    void playQuest_withSpecialQuest_addsQuestItemsToPlayer()
    {
        this.player.playQuest(this.specialQuest);
        assertTrue(this.player.getItems().contains(this.item));
    }

    @Test
    void levelUp_withEnoughExperiencePoints_increasesLevel()
    {
        this.player.setExperiencePoints(200);
        this.player.levelUp();
        assertEquals(2, this.player.getLevel());
    }
}
