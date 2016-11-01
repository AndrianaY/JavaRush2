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

    public static String askCurrencyCode(){
        while (true) {
            writeMessage("Введите код валюты: ");
            String str = readString();
            if (str.length() != 3) {
                writeMessage("Неверный код валюты! Введите заново.");
            } else {
                return str.toUpperCase();
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode){
        while (true) {
            writeMessage("Введите два целых положительных числа. Первое - номинал, второе - количество банкнот: ");
            String userInput = readString();
            String[] strmas = userInput.split(" ");
            try {
                if (strmas.length == 2 && Integer.parseInt(strmas[0]) >= 0 && Integer.parseInt(strmas[1]) >= 0) {
                    return strmas;
                } else {
                    writeMessage("Введены неверные данные, повторите ввод!");
                }
            }catch (NumberFormatException ex) {
                writeMessage("Введены неверные данные, повторите ввод!");
            }
        }
    }
}
