package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by Andriana_Yarmoliuk on 11/7/2016.
 */
public class Provider
{
    private Strategy strategy;
    public List<Vacancy> getJavaVacancies(String searchString){

        return strategy.getVacancies(searchString);
    }

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
}
