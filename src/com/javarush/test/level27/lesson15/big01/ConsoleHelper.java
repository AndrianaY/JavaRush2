package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriana_Yarmoliuk on 11/3/2016.
 */
public class ConsoleHelper
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException
    {
        return br.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException{
        List<Dish> list = new ArrayList<>();
        if (Dish.values().length > 0) {
            writeMessage(Dish.allDishesToString());
            String nextDish;
            while (!"exit".equalsIgnoreCase(nextDish = readString())) {
                try {
                    list.add(Dish.valueOf(nextDish));
                } catch (IllegalArgumentException e) {
                    writeMessage(nextDish + " is not detected");
                }
            }
        }
        return list;
    }
}
