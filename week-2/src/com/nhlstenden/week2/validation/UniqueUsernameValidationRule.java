package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.storage.UserStorage;
import com.nhlstenden.week2.user.User;

public class UniqueUsernameValidationRule implements ValidationRule
{
    private UserStorage storage;

    public UniqueUsernameValidationRule(UserStorage storage)
    {
        this.setStorage(storage);
    }

    public UserStorage getStorage()
    {
        return this.storage;
    }

    public void setStorage(UserStorage storage)
    {
        if (storage == null)
        {
            throw new IllegalArgumentException("storage cannot be null");
        }

        this.storage = storage;
    }

    @Override
    public boolean isValid(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        return !this.storage.containsName(user.getName());
    }

    @Override
    public String getDescription()
    {
        return "Username must not be taken by another user";
    }
}
