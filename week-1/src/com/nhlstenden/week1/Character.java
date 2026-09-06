package com.nhlstenden.week1;

public abstract class Character
{
    private static final int MIN_STAT_VALUE = 0;
    private String name;
    private int healthPoints;
    private int attackPower;
    private int defencePower;
    private boolean specialAbilityActive;

    public Character(String name, int healthPoints, int attackPower, int defencePower)
    {
        this.setName(name);
        this.setHealthPoints(healthPoints);
        this.setAttackPower(attackPower);
        this.setDefencePower(defencePower);
        this.setSpecialAbilityActive(false);
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

    public int getHealthPoints()
    {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints)
    {
        if (healthPoints < MIN_STAT_VALUE)
        {
            throw new IllegalArgumentException("healthPoints cannot be lower than " + MIN_STAT_VALUE);
        }

        this.healthPoints = healthPoints;
    }

    public int getAttackPower()
    {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower)
    {
        if (attackPower < MIN_STAT_VALUE)
        {
            throw new IllegalArgumentException("attackPower cannot be lower than " + MIN_STAT_VALUE);
        }

        this.attackPower = attackPower;
    }

    public int getDefencePower()
    {
        return this.defencePower;
    }

    public void setDefencePower(int defencePower)
    {
        if (defencePower < MIN_STAT_VALUE)
        {
            throw new IllegalArgumentException("defencePower cannot be lower than " + MIN_STAT_VALUE);
        }

        this.defencePower = defencePower;
    }

    public boolean isSpecialAbilityActive()
    {
        return this.specialAbilityActive;
    }

    public void setSpecialAbilityActive(boolean specialAbilityActive)
    {
        this.specialAbilityActive = specialAbilityActive;
    }

    public void attack(Character target)
    {
        if (target == null)
        {
            throw new IllegalArgumentException("target cannot be null");
        }

        target.defend(this.countDamage());
    }

    public void defend(int incomingDamage)
    {
        if (incomingDamage < MIN_STAT_VALUE)
        {
            throw new IllegalArgumentException("incomingDamage cannot be lower than " + MIN_STAT_VALUE);
        }

        int damageTaken = Math.max(MIN_STAT_VALUE, incomingDamage - this.defencePower);
        this.setHealthPoints(Math.max(MIN_STAT_VALUE, this.healthPoints - damageTaken));
    }

    public int countDamage()
    {
        return this.attackPower;
    }

    public abstract void useSpecialAbility();

    public void resetSpecialAbility()
    {
        this.setSpecialAbilityActive(false);
    }

    public boolean isAlive()
    {
        return this.healthPoints > MIN_STAT_VALUE;
    }
}
