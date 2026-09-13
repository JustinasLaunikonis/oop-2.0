package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

public class MinimumAgeValidationRule implements ValidationRule
{
    private static final int MIN_MINIMUM_AGE_IN_YEARS = 0;
    private int minimumAgeInYears;

    public MinimumAgeValidationRule(int minimumAgeInYears)
    {
        this.setMinimumAgeInYears(minimumAgeInYears);
    }

    public int getMinimumAgeInYears()
    {
        return this.minimumAgeInYears;
    }

    public void setMinimumAgeInYears(int minimumAgeInYears)
    {
        if (minimumAgeInYears < MIN_MINIMUM_AGE_IN_YEARS)
        {
            throw new IllegalArgumentException("minimumAgeInYears cannot be lower than " + MIN_MINIMUM_AGE_IN_YEARS);
        }

        this.minimumAgeInYears = minimumAgeInYears;
    }

    @Override
    public boolean isValid(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        return user.getAgeInYears(user.getDateOfBirth()) >= this.minimumAgeInYears;
    }

    @Override
    public String getDescription()
    {
        return "User must be at least " + this.minimumAgeInYears + " years old";
    }
}
