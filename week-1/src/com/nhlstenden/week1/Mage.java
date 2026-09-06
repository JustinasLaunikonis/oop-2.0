package com.nhlstenden.week1;

public class Mage extends Character
{
    private static final int DEFENCE_POWER_BOOST = 10;

    public Mage(String name, int healthPoints, int attackPower, int defencePower)
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

        this.setDefencePower(this.getDefencePower() + DEFENCE_POWER_BOOST);
        this.setSpecialAbilityActive(true);
    }

    @Override
    public void resetSpecialAbility()
    {
        if (this.isSpecialAbilityActive())
        {
            this.setDefencePower(this.getDefencePower() - DEFENCE_POWER_BOOST);
        }

        super.resetSpecialAbility();
    }
}
