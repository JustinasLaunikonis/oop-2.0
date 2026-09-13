package com.nhlstenden.week2.storage;

import com.nhlstenden.week2.user.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserStorage implements UserStorage
{
    private List<User> users;

    public InMemoryUserStorage()
    {
        this.setUsers(new ArrayList<>());
    }

    @Override
    public List<User> getUsers()
    {
        return this.users;
    }

    public void setUsers(List<User> users)
    {
        if (users == null)
        {
            throw new IllegalArgumentException("users cannot be null");
        }

        this.users = users;
    }

    @Override
    public void save(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        if (this.containsName(user.getName()))
        {
            throw new IllegalArgumentException("a user with name " + user.getName() + " is already stored");
        }

        this.users.add(user);
    }

    @Override
    public boolean containsName(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name cannot be null");
        }

        return this.findByName(name) != null;
    }

    @Override
    public User findByName(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name cannot be null");
        }

        for (User user : this.users)
        {
            if (user.getName().equals(name))
            {
                return user;
            }
        }

        return null;
    }
}
