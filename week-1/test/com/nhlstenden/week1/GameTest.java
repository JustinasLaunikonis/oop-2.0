package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    private Game game;
    private Player player;
    private Quest easyQuest;
    private Quest hardQuest;

    @BeforeEach
    void setup()
    {
        this.game = new Game();
        this.player = new Player("Justinas", new Warrior("Aldric", 100, 20, 5));
        this.easyQuest = new Quest("Clear the cellar", 1, 50, new Warrior("Goblin", 50, 8, 3));
        this.hardQuest = new Quest("Slay the dragon", 3, 100, new Warrior("Dragon", 300, 40, 20));
    }

    @Test
    void addQuest_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.game.addQuest(null));
    }

    @Test
    void addPlayer_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.game.addPlayer(null));
    }

    @Test
    void availableQuestsFor_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.game.availableQuestsFor(null));
    }

    @Test
    void availableQuestsFor_withoutQuests_returnsEmptyList()
    {
        assertTrue(this.game.availableQuestsFor(this.player).isEmpty());
    }

    @Test
    void availableQuestsFor_withNewPlayer_returnsOnlyUnlockedQuests()
    {
        this.game.addQuest(this.easyQuest);
        this.game.addQuest(this.hardQuest);
        assertEquals(1, this.game.availableQuestsFor(this.player).size());
        assertTrue(this.game.availableQuestsFor(this.player).contains(this.easyQuest));
    }
}
