package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import java.util.Collection;

/**
 * Created by Andriana on 02.11.2016.
 */
class InfoCommand implements Command
{
    @Override
    public void execute()
    {
        Collection<CurrencyManipulator> manipulatorCollection = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulatorCollection.isEmpty())
            ConsoleHelper.writeMessage("No money available.");
        else
        {
            int count=0;
            for (CurrencyManipulator manipulator : manipulatorCollection)
            {
                if (manipulator.hasMoney() && manipulator.getTotalAmount()>0)
                {
                    ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                    count++;
                }
            }
            if (count==0)
                ConsoleHelper.writeMessage("No money available.");
        }
    }
}
