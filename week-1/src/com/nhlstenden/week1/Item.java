package com.nhlstenden.week1;

public class Item
{
    private String title;

    public Item(String title)
    {
        this.setTitle(title);
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        if (title == null)
        {
            throw new IllegalArgumentException("title cannot be null");
        }

        if (title.isBlank())
        {
            throw new IllegalArgumentException("title cannot be blank");
        }

        this.title = title;
    }
}
