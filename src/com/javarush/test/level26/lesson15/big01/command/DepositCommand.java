package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by Andriana on 02.11.2016.
 */
class DepositCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        try {
            String curCode = ConsoleHelper.askCurrencyCode();
            String[] str = ConsoleHelper.getValidTwoDigits(curCode);
            CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode);
            manipulator.addAmount(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
}
