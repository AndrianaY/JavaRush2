package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;
//3. Вернемся к классу Order: в нем есть ссылка на планшет, и еще есть список выбранных блюд.
//        Создайте поле dishes - список блюд. Инициализируйте его в конструкторе, вызвав метод getAllDishesForOrder из ConsoleHelper.
//
//        4. Перепишите метод toString в классе Order. Пусть он возвращает пустую строку, если нет блюд в заказе, иначе
//        вывод должен быть аналогичный примеру в порядке добавления блюд. Используйте ConsoleHelper.
//        Пример:
//        Your order: [Juice, Fish] of Tablet{number=5}
//
//        5. У нас все завязано на работу с консолью. Однако, при возникновении исключений, наше приложение умрет.
//        Чтобы узнать причину - добавим в Tablet статический логгер java.util.logging.Logger, инициализированный именем класса.
//
//        6. В методе createOrder класса Tablet обработаем исключения ввода-вывода.
//        Запишем в лог "Console is unavailable.". Уровень лога - SEVERE - это самый серьезный уровень, мы не можем работать.
//
//        7. Надо начинать тестировать наше приложение.
//        Добавьте в main создание планшета и создание заказа - new Tablet(5).createOrder();
/**
 * Created by Andriana_Yarmoliuk on 11/3/2016.
 */
public class Order
{
    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    private static List<Dish> dishes;
    private Tablet tablet;

    @Override
    public String toString()
    {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of Tablet{number=%d}", dishes, tablet.getNumber());
    }

    public int getTotalCookingTime(){
        int time = 0;
        for (Dish d:dishes)
        {
            time += d.getDuration();
        }
        return time;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

}
