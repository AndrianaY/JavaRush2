package com.javarush.test.level26.lesson15.big01;

/**
 * Created by Andriana_Yarmoliuk on 11/1/2016.
 */
public enum Operation
{
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        if (i >= 1 && i <= 4) {
            return Operation.values()[i];
        } else throw new IllegalArgumentException();
    }
}
