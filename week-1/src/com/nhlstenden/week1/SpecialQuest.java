package com.nhlstenden.week1;

import java.util.ArrayList;

public class SpecialQuest extends Quest
{
    private ArrayList<Item> items;

    public SpecialQuest(String title, int difficulty, int experiencePointsReward, Character enemy)
    {
        super(title, difficulty, experiencePointsReward, enemy);
        this.setItems(new ArrayList<>());
    }

    public ArrayList<Item> getItems()
    {
        return this.items;
    }

    public void setItems(ArrayList<Item> items)
    {
        if (items == null)
        {
            throw new IllegalArgumentException("items cannot be null");
        }

        this.items = items;
    }

    public void addItem(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("item cannot be null");
        }

        this.items.add(item);
    }
}
