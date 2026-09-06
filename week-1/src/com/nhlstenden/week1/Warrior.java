package com.nhlstenden.week1;

public class Warrior extends Character
{
    private static final int ATTACK_POWER_BOOST = 10;

    public Warrior(String name, int healthPoints, int attackPower, int defencePower)
    {
        super(name, healthPoints, attackPower, defencePower);
    }

    @Override
    public void useSpecialAbility()
    {
        if (this.isSpecialAbilityActive())
        {
            return;
        }

        this.setAttackPower(this.getAttackPower() + ATTACK_POWER_BOOST);
        this.setSpecialAbilityActive(true);
    }

    @Override
    public void resetSpecialAbility()
    {
        if (this.isSpecialAbilityActive())
        {
            this.setAttackPower(this.getAttackPower() - ATTACK_POWER_BOOST);
        }

        super.resetSpecialAbility();
    }
}
