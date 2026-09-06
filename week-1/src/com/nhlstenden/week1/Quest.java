package com.nhlstenden.week1;

public class Quest
{
    private static final int XP_PER_DIFFICULTY_LEVEL = 10;
    private static final int MIN_DIFFICULTY = 1;
    private static final int MAX_DIFFICULTY = 10;
    private static final int MIN_EXPERIENCE_POINTS_REWARD = 0;
    private String title;
    private int difficulty;
    private int experiencePointsReward;
    private Character enemy;

    public Quest(String title, int difficulty, int experiencePointsReward, Character enemy)
    {
        this.setTitle(title);
        this.setDifficulty(difficulty);
        this.setExperiencePointsReward(experiencePointsReward);
        this.setEnemy(enemy);
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        if (title == null)
        {
            throw new IllegalArgumentException("title cannot be null");
        }

        if (title.isBlank())
        {
            throw new IllegalArgumentException("title cannot be blank");
        }

        this.title = title;
    }

    public int getDifficulty()
    {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty)
    {
        if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY)
        {
            throw new IllegalArgumentException("difficulty must be between " + MIN_DIFFICULTY + " and " + MAX_DIFFICULTY);
        }

        this.difficulty = difficulty;
    }

    public int getExperiencePointsReward()
    {
        return this.experiencePointsReward;
    }

    public void setExperiencePointsReward(int experiencePointsReward)
    {
        if (experiencePointsReward < MIN_EXPERIENCE_POINTS_REWARD)
        {
            throw new IllegalArgumentException("experiencePointsReward cannot be lower than " + MIN_EXPERIENCE_POINTS_REWARD);
        }

        this.experiencePointsReward = experiencePointsReward;
    }

    public Character getEnemy()
    {
        return this.enemy;
    }

    public void setEnemy(Character enemy)
    {
        if (enemy == null)
        {
            throw new IllegalArgumentException("enemy cannot be null");
        }

        this.enemy = enemy;
    }

    public int requiredExperiencePoints()
    {
        return (this.difficulty - MIN_DIFFICULTY) * XP_PER_DIFFICULTY_LEVEL;
    }
}
