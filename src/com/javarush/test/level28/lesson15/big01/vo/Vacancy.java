package com.javarush.test.level28.lesson15.big01.vo;
//3. Что есть у вакансии?
//        Название, зарплата, город, название компании, название сайта, на котором вакансия найдена, ссылка на вакансию.
//        В классе Vacancy создай соответствующие строковые поля: title, salary, city, companyName, siteName, url.
//
//        4. Создай геттеры и сеттеры для всех полей класса Vacancy.
//
//        5. В пакете model создай класс HHStrategy от Strategy.
//        Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).

/**
 * Created by Andriana_Yarmoliuk on 11/7/2016.
 */
public class Vacancy
{
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;

        Vacancy vacancy = (Vacancy) o;

        if (!getTitle().equals(vacancy.getTitle())) return false;
        if (!getSalary().equals(vacancy.getSalary())) return false;
        if (!getCity().equals(vacancy.getCity())) return false;
        if (!getCompanyName().equals(vacancy.getCompanyName())) return false;
        if (!getSiteName().equals(vacancy.getSiteName())) return false;
        return getUrl().equals(vacancy.getUrl());

    }

    @Override
    public int hashCode()
    {
        int result = getTitle().hashCode();
        result = 31 * result + getSalary().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getCompanyName().hashCode();
        result = 31 * result + getSiteName().hashCode();
        result = 31 * result + getUrl().hashCode();
        return result;
    }

    private String title, salary, city, companyName, siteName, url;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
