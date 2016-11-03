package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andriana on 02.11.2016.
 */
public class CommandExecutor
{
    private static Map<Operation, Command> commands = new HashMap<Operation, Command>()
    {{
        put(Operation.INFO, new InfoCommand());
        put(Operation.DEPOSIT, new DepositCommand());
        put(Operation.WITHDRAW, new WithdrawCommand());
        put(Operation.EXIT, new ExitCommand());
    }};
    public static final void execute(Operation operation) throws InterruptOperationException
    {
        commands.get(operation).execute();
    }
     private CommandExecutor(){

    }
}
