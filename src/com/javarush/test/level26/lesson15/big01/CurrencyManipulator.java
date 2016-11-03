package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Andriana_Yarmoliuk on 11/1/2016.
 */
public class CurrencyManipulator
{
    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public boolean hasMoney(){
        if (denominations.isEmpty()) return false;
        else {
            for (Map.Entry<Integer,Integer> pair : denominations.entrySet()){
                if (pair.getValue()!=0) return true;
            }
            return false;
        }
    }
    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }
// 2. Логика основного метода withdrawAmount:
//        2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
//        Используйте Жадный алгоритм (use google).
//        Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
//        Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
//        Пример, надо выдать 600
//        В манипуляторе есть следующие банкноты:
//        500 - 2
//        200 - 3
//        100 - 1
//        50 - 12
//        Результат должен быть таким:
//        500 - 1
//        100 - 1
//        т.е. всего две банкноты (это минимальное количество банкнот) номиналом 500 и 100.
//
//        2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
//        если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.
//
//        2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.
//        Пример, надо выдать 600
//        В манипуляторе есть следующие банкноты:
//        500 - 2
//        200 - 2
//        Результат - данными банкнотами невозможно выдать запрашиваемую сумму. Кинуть исключение.
//        Не забудьте, что в некоторых случаях картой кидается ConcurrentModificationException.

    public Map<Integer, Integer> withdrawAmount(int expectedAmount)throws NotEnoughMoneyException{

        int amount, nom, count, diff; //q - вспомогательная переменная в цикле
        amount = expectedAmount;
        count=0; //количество купюр,  ему по умолчанию присваивается 0
        diff=0;
        TreeMap<Integer, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(denominations);
        TreeMap<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());

        for(Map.Entry<Integer, Integer> m: sorted.entrySet()) //цикл перебирает все элементы массива, от большего к меньшему
        {
            nom = m.getKey();
            count += amount / nom; //считаем, сколько раз входит купюра в сумму
            while (true){
                if (count > m.getValue())
                {
                    count--;
                    diff++;
                }
                else
                    break;
            }
            amount = (amount % nom) + diff * nom;  //сумме присваивается остаток от деления на данную купюр
            result.put(nom, count);
        }
        if(amount > 0) //проверяем наличие остатка, который банкомат не сможет выдать
            throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> map:result.entrySet())
        {
            denominations.put(map.getKey(), (denominations.get(map.getKey()) - map.getValue())) ;
        }
        return result;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }
    public int getTotalAmount(){
        Integer amount = 0;
        for (Map.Entry entry: denominations.entrySet())
        {
            int denoms = (int) entry.getKey();
            int count = (int) entry.getValue();
            amount = amount + (denoms * count);
        }
        return amount;
    }
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public void addAmount(int denomination, int count)
    {
        if (denominations==null)
            denominations= new HashMap<>();
        if (denominations.keySet().contains(denomination))
        {
            denominations.put(denomination,denominations.get(denomination)+count);
        }
        else
            denominations.put(denomination,count);
    }
}
