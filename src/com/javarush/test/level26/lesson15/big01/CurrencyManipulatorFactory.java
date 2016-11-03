package com.javarush.test.level26.lesson15.big01;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andriana_Yarmoliuk on 11/1/2016.
 */
public final class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> manipulators = new HashMap<>();
    private CurrencyManipulatorFactory(){}

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (manipulators.containsKey(currencyCode)) {
            return manipulators.get(currencyCode);
        } else {
            CurrencyManipulator cur = new CurrencyManipulator(currencyCode);
            manipulators.put(currencyCode, cur);
            return cur;
        }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return manipulators.values();
    }


}
