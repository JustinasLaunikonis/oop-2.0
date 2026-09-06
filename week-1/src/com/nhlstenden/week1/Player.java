package com.nhlstenden.week1;

import java.util.ArrayList;

public class Player
{
    private static final int EXPERIENCE_POINTS_TO_LEVEL_UP = 200;
    private static final int STARTING_LEVEL = 1;
    private static final int STARTING_EXPERIENCE_POINTS = 0;
    private String name;
    private Character character;
    private int experiencePoints;
    private int level;
    private ArrayList<Item> items;

    public Player(String name, Character character)
    {
        this.setName(name);
        this.setCharacter(character);
        this.setExperiencePoints(STARTING_EXPERIENCE_POINTS);
        this.setLevel(STARTING_LEVEL);
        this.setItems(new ArrayList<>());
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name cannot be null");
        }

        if (name.isBlank())
        {
            throw new IllegalArgumentException("name cannot be blank");
        }

        this.name = name;
    }

    public Character getCharacter()
    {
        return this.character;
    }

    public void setCharacter(Character character)
    {
        if (character == null)
        {
            throw new IllegalArgumentException("character cannot be null");
        }

        this.character = character;
    }

    public int getExperiencePoints()
    {
        return this.experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints)
    {
        if (experiencePoints < STARTING_EXPERIENCE_POINTS)
        {
            throw new IllegalArgumentException("experiencePoints cannot be lower than " + STARTING_EXPERIENCE_POINTS);
        }

        this.experiencePoints = experiencePoints;
    }

    public int getLevel()
    {
        return this.level;
    }

    public void setLevel(int level)
    {
        if (level < STARTING_LEVEL)
        {
            throw new IllegalArgumentException("level cannot be lower than " + STARTING_LEVEL);
        }

        this.level = level;
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

    public boolean canPlayQuest(Quest quest)
    {
        if (quest == null)
        {
            throw new IllegalArgumentException("quest cannot be null");
        }

        return this.character.isAlive() && this.experiencePoints >= quest.requiredExperiencePoints();
    }

    public void playQuest(Quest quest)
    {
        if (quest == null)
        {
            throw new IllegalArgumentException("quest cannot be null");
        }

        if (!this.canPlayQuest(quest))
        {
            throw new IllegalArgumentException("player cannot play quest " + quest.getTitle());
        }

        Character enemy = quest.getEnemy();
        this.character.useSpecialAbility();

        while (this.character.isAlive() && enemy.isAlive() && this.canAnyoneDealDamage(enemy))
        {
            this.character.attack(enemy);

            if (enemy.isAlive())
            {
                enemy.attack(this.character);
            }
        }

        this.character.resetSpecialAbility();

        if (!enemy.isAlive())
        {
            this.collectReward(quest);
        }
    }

    public void addItem(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("item cannot be null");
        }

        this.items.add(item);
    }

    public boolean canLevelUp()
    {
        return this.experiencePoints >= this.level * EXPERIENCE_POINTS_TO_LEVEL_UP;
    }

    public void levelUp()
    {
        if (!this.canLevelUp())
        {
            throw new IllegalStateException("player needs " + this.level * EXPERIENCE_POINTS_TO_LEVEL_UP + " experience points to level up");
        }

        this.setLevel(this.level + 1);
    }

    private boolean canAnyoneDealDamage(Character enemy)
    {
        boolean playerCanHurtEnemy = this.character.countDamage() > enemy.getDefencePower();
        boolean enemyCanHurtPlayer = enemy.countDamage() > this.character.getDefencePower();

        return playerCanHurtEnemy || enemyCanHurtPlayer;
    }

    private void collectReward(Quest quest)
    {
        this.setExperiencePoints(this.experiencePoints + quest.getExperiencePointsReward());

        if (quest instanceof SpecialQuest)
        {
            for (Item item : ((SpecialQuest) quest).getItems())
            {
                this.addItem(item);
            }
        }
    }
}
