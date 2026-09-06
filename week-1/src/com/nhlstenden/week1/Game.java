package com.nhlstenden.week1;

import java.util.ArrayList;

public class Game
{
    private ArrayList<Quest> quests;
    private ArrayList<Player> players;

    public Game()
    {
        this.setQuests(new ArrayList<>());
        this.setPlayers(new ArrayList<>());
    }

    public ArrayList<Quest> getQuests()
    {
        return this.quests;
    }

    public void setQuests(ArrayList<Quest> quests)
    {
        if (quests == null)
        {
            throw new IllegalArgumentException("quests cannot be null");
        }

        this.quests = quests;
    }

    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    public void setPlayers(ArrayList<Player> players)
    {
        if (players == null)
        {
            throw new IllegalArgumentException("players cannot be null");
        }

        this.players = players;
    }

    public void addQuest(Quest quest)
    {
        if (quest == null)
        {
            throw new IllegalArgumentException("quest cannot be null");
        }

        this.quests.add(quest);
    }

    public void addPlayer(Player player)
    {
        if (player == null)
        {
            throw new IllegalArgumentException("player cannot be null");
        }

        this.players.add(player);
    }

    public ArrayList<Quest> availableQuestsFor(Player player)
    {
        if (player == null)
        {
            throw new IllegalArgumentException("player cannot be null");
        }

        ArrayList<Quest> availableQuests = new ArrayList<>();

        for (Quest quest : this.quests)
        {
            if (player.canPlayQuest(quest))
            {
                availableQuests.add(quest);
            }
        }

        return availableQuests;
    }
}
