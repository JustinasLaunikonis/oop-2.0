package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

public interface ValidationRule
{
    boolean isValid(User user);

    String getDescription();
}
