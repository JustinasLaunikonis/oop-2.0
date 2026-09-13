package com.nhlstenden.week2.storage;

import com.nhlstenden.week2.user.User;

import java.util.List;

public interface UserStorage
{
    void save(User user);

    boolean containsName(String name);

    User findByName(String name);

    List<User> getUsers();
}
