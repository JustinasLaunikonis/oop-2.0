package com.nhlstenden.week2.user;

import java.time.LocalDate;
import java.time.Period;

public class User
{
    private String name;
    private String password;
    private String email;
    private LocalDate dateOfBirth;

    public User(String name, String password, String email, LocalDate dateOfBirth)
    {
        this.setName(name);
        this.setPassword(password);
        this.setEmail(email);
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException("name cannot be null");
        }

        if (name.isBlank())
        {
            throw new IllegalArgumentException("name cannot be blank");
        }

        this.name = name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        if (password == null)
        {
            throw new IllegalArgumentException("password cannot be null");
        }

        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        if (email == null)
        {
            throw new IllegalArgumentException("email cannot be null");
        }

        this.email = email;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("dateOfBirth cannot be null");
        }

        if (dateOfBirth.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("dateOfBirth cannot be in the future");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public int getAgeInYears(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("dateOfBirth cannot be null");
        }

        if (dateOfBirth.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("dateOfBirth cannot be in the future");
        }

        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
