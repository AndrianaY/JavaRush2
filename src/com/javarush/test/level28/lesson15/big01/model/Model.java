package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriana_Yarmoliuk on 11/8/2016.
 */
public class Model
{
    private View view;

    public Model(View view, Provider... providers)
    {
        if (view == null || providers.length == 0)
            throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    private Provider[] providers;

    public void selectCity(String city){
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider p:providers )
        {
            vacancies.addAll(p.getJavaVacancies(city));
        }
        view.update(vacancies);
    }
}
