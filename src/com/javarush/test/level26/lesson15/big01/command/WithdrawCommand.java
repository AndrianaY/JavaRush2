package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
//Задание 10
//        Сегодня мы займемся командой WithdrawCommand - это самая сложная операция.
//
//        1. Реализуйте следующий алгоритм для команды WithdrawCommand:
//        1.1. Считать код валюты (метод уже есть)
//        1.2. Получить манипулятор для этой валюты.
//        1.3. Пока пользователь не введет корректные данные выполнять:
//        1.3.1. Попросить ввести сумму
//        1.3.2. Если введены некорректные данные, то сообщить об этом пользователю и вернуться к п. 1.3.
//        1.3.3. Проверить, достаточно ли средств на счету.
//        Для этого в манипуляторе создайте метод boolean isAmountAvailable(int expectedAmount), который вернет true, если денег достаточно для выдачи.
//        Если недостаточно, то вернуться к п. 1.3.
//        1.3.4. Списать деньги со счета. Для этого в манируляторе создайте метод
//        Map<Integer, Integer> withdrawAmount(int expectedAmount), который вернет карту Map<номинал, количество>.
//        Подробно логику этого метода смотрите в п.2.
//        1.3.5. Вывести пользователю результат из п. 1.3.4. в следующем виде:
//        <табуляция>номинал - количество
//        Сортировка - от большего номинала к меньшему.
//        Вывести сообщение об успешной транзакции.
//        1.3.6. Перехватить исключение NotEnoughMoneyException, уведомить юзера о нехватке банкнот и вернуться к п. 1.3.
//
//        2. Логика основного метода withdrawAmount:
//        2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
//        Используйте Жадный алгоритм (use google).
//        Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
//        Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
//        Пример, надо выдать 600
//        В манипуляторе есть следующие банкноты:
//        500 - 2
//        200 - 3
//        100 - 1
//        50 - 12
//        Результат должен быть таким:
//        500 - 1
//        100 - 1
//        т.е. всего две банкноты (это минимальное количество банкнот) номиналом 500 и 100.
//
//        2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
//        если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.
//
//        2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.
//        Пример, надо выдать 600
//        В манипуляторе есть следующие банкноты:
//        500 - 2
//        200 - 2
//        Результат - данными банкнотами невозможно выдать запрашиваемую сумму. Кинуть исключение.
//        Не забудьте, что в некоторых случаях картой кидается ConcurrentModificationException.

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
        while (true)
        {
            ConsoleHelper.writeMessage("enter amount");
            amount = Integer.parseInt(ConsoleHelper.readString());
            if ((amount > 0) && manipulator.isAmountAvailable(amount))
                break;
        }

        TreeMap<Integer, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        try {
            sorted.putAll(manipulator.withdrawAmount(amount));
            for (Map.Entry<Integer, Integer> map : sorted.entrySet()) {
                ConsoleHelper.writeMessage("    " + map.getKey() + " - " + map.getValue());
            }
            ConsoleHelper.writeMessage("operation was succsesfull");
        }
        catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage("not enough money");
        }
    }
}
