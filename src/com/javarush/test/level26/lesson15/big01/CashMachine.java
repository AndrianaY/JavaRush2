package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by Andriana_Yarmoliuk on 11/1/2016.
 */
public class CashMachine
{
    public static void main(String[] args){
        try{
            Locale.setDefault(Locale.ENGLISH);
            Operation operation;
            do{
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        }
        catch (InterruptOperationException e){
            ConsoleHelper.writeMessage("bye");
        }
    }
}
