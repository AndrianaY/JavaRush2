package com.javarush.test.level26.lesson15.big01;

import java.util.Locale;

/**
 * Created by Andriana_Yarmoliuk on 11/1/2016.
 */
public class CashMachine
{
    public static void main(String[] args){
        Locale.setDefault(Locale.ENGLISH);
        Operation op = Operation.DEPOSIT;
        CurrencyManipulator manipulator = null;
        switch (op) {
            case DEPOSIT: {
                try {
                    String curCode = ConsoleHelper.askCurrencyCode();
                    String[] str = ConsoleHelper.getValidTwoDigits(curCode);
                    manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode);
                    manipulator.addAmount(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        }
        manipulator.getTotalAmount();
        ConsoleHelper.askOperation();
        
    }
}
