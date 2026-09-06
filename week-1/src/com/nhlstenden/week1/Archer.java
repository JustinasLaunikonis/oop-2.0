package com.nhlstenden.week1;

public class Archer extends Character
{
    private static final int DAMAGE_MULTIPLIER = 2;

    public Archer(String name, int healthPoints, int attackPower, int defencePower)
    {
        super(name, healthPoints, attackPower, defencePower);
    }

    @Override
    public void useSpecialAbility()
    {
        this.setSpecialAbilityActive(true);
    }

    @Override
    public int countDamage()
    {
        if (this.isSpecialAbilityActive())
        {
            return this.getAttackPower() * DAMAGE_MULTIPLIER;
        }

        return this.getAttackPower();
    }
}
