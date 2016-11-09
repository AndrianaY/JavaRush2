package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Andriana_Yarmoliuk on 11/1/2016.
 */
public class ConsoleHelper
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String line;
        while (true)
        {
            try
            {
                line = br.readLine();
            }
            catch (IOException e)
            {
                continue;
            }
            if (line.equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
            else return line;
        }
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        writeMessage("write operation number 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
        while (true){
            String operation = readString();
            try
            {
                if (Integer.parseInt(operation) > 0 && Integer.parseInt(operation) <= 4)
                    return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
                else
                {
                    writeMessage("enter operation again 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT ");
                }
            }
            catch (Exception e){
                writeMessage("enter operation again 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT ");
            }
        }
    }
    public static String askCurrencyCode() throws InterruptOperationException
    {
        while (true) {
            writeMessage("Enter currency code");
            String str = readString();
            if (str.length() != 3) {
                writeMessage("Wrong code. Try again");
            } else {
                return str.toUpperCase();
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        while (true) {
            writeMessage("Enter denomination and count ");
            String userInput = readString();
            String[] strmas = userInput.split(" ");
            try {
                if (strmas.length == 2 && Integer.parseInt(strmas[0]) >= 0 && Integer.parseInt(strmas[1]) >= 0) {
                    return strmas;
                } else {
                    writeMessage("Wrong data. Try again");
                }
            }catch (NumberFormatException ex) {
                writeMessage("Wrong data. Try again");
            }
        }
    }
}
