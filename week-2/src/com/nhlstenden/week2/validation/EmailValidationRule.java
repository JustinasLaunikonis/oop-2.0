package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

public class EmailValidationRule implements ValidationRule
{
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$";

    public EmailValidationRule()
    {
    }

    @Override
    public boolean isValid(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        return user.getEmail().matches(EMAIL_PATTERN);
    }

    @Override
    public String getDescription()
    {
        return "Email must look like name@domain.extension";
    }
}
