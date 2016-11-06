package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Andriana on 02.11.2016.
 */
class WithdrawCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException
    {
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        int amount;
        ConsoleHelper.writeMessage("enter amount");
        while (true)
        {
            try
            {
                amount = Integer.parseInt(ConsoleHelper.readString());
                if ((amount <= 0))
                {
                    ConsoleHelper.writeMessage("enter correct amount");
                    return;
                }
                if (!manipulator.isAmountAvailable(amount)) throw new NotEnoughMoneyException();
                TreeMap<Integer, Integer> sorted = new TreeMap<>(Collections.reverseOrder());

                sorted.putAll(manipulator.withdrawAmount(amount));
                for (Map.Entry<Integer, Integer> map : sorted.entrySet()) {
                    if (!(map.getValue() == 0))
                        ConsoleHelper.writeMessage("\t" + map.getKey() + " - " + map.getValue());
                }
                ConsoleHelper.writeMessage("operation was succsesfull");
                break;
            }
            catch (NotEnoughMoneyException e)
            {
                ConsoleHelper.writeMessage("not enough money");
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage("Incorrect sum!");
            }
        }
    }
}
