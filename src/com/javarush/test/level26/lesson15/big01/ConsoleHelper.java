package com.javarush.test.level26.lesson15.big01;
//1. Создадим в ConsoleHelper два статических метода:
//        1.1 writeMessage(String message), который будет писать в консоль наше сообщение
//        1.2 String readString(), который будет считывать с консоли строку и возвращать ее.
//        Если возникнет какое-то исключение при работе с консолью, то перехватим его и не будем обрабатывать.
//        Кстати, создадим только один экземпляр BufferedReader-а
//
//        2. Создадим пакет exception, в который поместим два checked исключения:
//        2.1 InterruptOperationException будем кидать, когда нужно прервать текущую операцию и выйти из приложения
//        2.2 NotEnoughMoneyException будем кидать, когда не сможем выдать запрашиваемую сумму

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


    public static String readString () {
        while (true) {
            try {
                return br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    public static Operation askOperation(){
        while (true){
            writeMessage("write operation number 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");
            String operation = readString();
            try
            {
                if (Integer.parseInt(operation) > 0 && Integer.parseInt(operation) <= 4)
                    return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
                else
                {
                    writeMessage("enter operation again1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT ");
                }
            }
            catch (Exception e){
                writeMessage("enter operation again1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT ");
            }
        }
    }
    public static String askCurrencyCode(){
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

    public static String[] getValidTwoDigits(String currencyCode){
        while (true) {
            writeMessage("Enter denomination and count ");
            String userInput = readString();
            String[] strmas = userInput.split(" ");
            try {
                if (strmas.length == 2 && Integer.parseInt(strmas[0]) >= 0 && Integer.parseInt(strmas[1]) >= 0) {
                    return strmas;
                } else {
                    writeMessage("Wrong code. Try again");
                }
            }catch (NumberFormatException ex) {
                writeMessage("Wrong code. Try again");
            }
        }
    }
}
