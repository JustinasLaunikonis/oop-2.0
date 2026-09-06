package com.nhlstenden.week1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest
{
    private Item item;

    @BeforeEach
    void setup()
    {
        this.item = new Item("Silver Sword");
    }

    @Test
    void constructor_withNullTitle_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new Item(null));
    }

    @Test
    void constructor_withBlankTitle_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new Item("   "));
    }

    @Test
    void constructor_withValidTitle_setsTitle()
    {
        assertEquals("Silver Sword", this.item.getTitle());
    }
}
