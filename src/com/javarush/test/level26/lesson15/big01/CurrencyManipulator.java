package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

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


    public Map<Integer, Integer> withdrawAmount(int expectedAmount)throws NotEnoughMoneyException{

        int amount, nom, count, diff; //q - вспомогательная переменная в цикле
        amount = expectedAmount;
        TreeMap<Integer, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(denominations);
        TreeMap<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());

        try
        {
            for (Map.Entry<Integer, Integer> m : sorted.entrySet())
            {
                count = 0; //количество купюр,  ему по умолчанию присваивается 0
                diff = 0;
                nom = m.getKey();
                count += amount / nom; //считаем, сколько раз входит купюра в сумму
                while (true)
                {
                    if (count > m.getValue())
                    {
                        count--;
                        diff++;
                    } else
                        break;
                }
                amount = (amount % nom) + diff * nom;  //сумме присваивается остаток от деления на данную купюр
                result.put(nom, count);
            }
            if (amount > 0) //проверяем наличие остатка, который банкомат не сможет выдать
                throw new NotEnoughMoneyException();
            for (Map.Entry<Integer, Integer> map : result.entrySet())
            {
                denominations.put(map.getKey(), (denominations.get(map.getKey()) - map.getValue()));
            }
        }
        catch (ConcurrentModificationException e){
            ConsoleHelper.writeMessage("concurrent exception");
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
        if (denominations.containsKey(denomination))
        {
            denominations.put(denomination,denominations.get(denomination)+count);
        }
        else
            denominations.put(denomination,count);
    }
}
