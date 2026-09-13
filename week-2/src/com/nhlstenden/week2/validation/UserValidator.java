package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.storage.UserStorage;
import com.nhlstenden.week2.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator
{
    private List<ValidationRule> rules;
    private UserStorage storage;

    public UserValidator(UserStorage storage)
    {
        this.setRules(new ArrayList<>());
        this.setStorage(storage);
    }

    public List<ValidationRule> getRules()
    {
        return this.rules;
    }

    public void setRules(List<ValidationRule> rules)
    {
        if (rules == null)
        {
            throw new IllegalArgumentException("rules cannot be null");
        }

        this.rules = rules;
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

    public void addRule(ValidationRule rule)
    {
        if (rule == null)
        {
            throw new IllegalArgumentException("rule cannot be null");
        }

        this.rules.add(rule);
    }

    public boolean isValid(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        return this.failedRules(user).isEmpty();
    }

    public List<ValidationRule> failedRules(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        List<ValidationRule> failedRules = new ArrayList<>();

        for (ValidationRule rule : this.rules)
        {
            if (!rule.isValid(user))
            {
                failedRules.add(rule);
            }
        }

        return failedRules;
    }

    public boolean register(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        if (!this.isValid(user))
        {
            return false;
        }

        this.storage.save(user);

        return true;
    }
}
