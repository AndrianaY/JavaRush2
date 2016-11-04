package com.javarush.test.level27.lesson15.big01.kitchen;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * Created by Andriana on 04.11.2016.
 */
public class Cook implements Observable
{
    private String name;

    @Override
    public String toString()
    {
        return  name;
    }

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public void addListener(InvalidationListener listener)
    {

    }

    @Override
    public void removeListener(InvalidationListener listener)
    {

    }
}
