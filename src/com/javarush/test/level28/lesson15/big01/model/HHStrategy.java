package com.javarush.test.level28.lesson15.big01.model;
//Задание 6
//
//        1. Слева в панеле Project найди снизу External Libraries. В jsoup найди пакет examples, посмотри классы в этом пакете.
//
//        2. По аналогии с реализацией в примерах кода jsoup - реализуй коннекшен к урлу ХэдХантера методом GET.
//        Это нужно сделать в методе getVacancies класса HHStrategy.
//        Подсказка: получится объект класса Document.
//
//        3. Поставь брекпоинт сразу после коннекшена. Запусти программу в дебаг моде.
//        Скопируй полученное значение document.html() в буффер.
//
//        4. Создай файл с расширением html где-то в корне JavaRushHomeWork.
//        Вставь содержимое буффера в этот файл и отформатируй его Ctrl+Alt+L, IDEA умеет форматировать HTML.
//        Ура! Это код страницы HTML :)
//
//        5. Реализуй в вакансии (Vacancy) методы equals/hashCode
//        Alt+Enter - equals() and hashCode()

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * Created by Andriana_Yarmoliuk on 11/7/2016.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%s";
    private static String ADDITIONAL_VALUE = "Kiev";
    private static String PAGE_VALUE = "1";
    private Document doc;


    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        try
        {
            doc = Jsoup.connect(String.format(URL_FORMAT, ADDITIONAL_VALUE, PAGE_VALUE)).
                    userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36").
                    referrer(String.format(URL_FORMAT, ADDITIONAL_VALUE, PAGE_VALUE)).
                    get();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
