package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import java.util.Observable;
import java.util.Observer;
//3. Метод void update(Observable observable, Object arg), который необходимо реализовать, принимает два параметра.
//        -observable - объект, который отправил нам значение
//        -arg - само значение, в нашем случае - это объект Order
//        На данном этапе мы сымитируем обработку и выведем в консоль "Start cooking - " + order

/**
 * Created by Andriana on 04.11.2016.
 */
public class Cook extends Observable implements Observer
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
    public void update(Observable o, Object order)
    {
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + ((Order) order).getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(order);
    }
}
