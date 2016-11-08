package com.javarush.test.level28.lesson15.big01;
//Задание 2
//
//        1. Создай класс Controller, в нем будет содержаться бизнес логика.
//
//        2. В Controller добавь паблик конструктор, который будет принимать столько провайдеров, сколько в него передадут для обработки.
//        Сохрани их в приватное поле providers.
//        Помнишь, как это делать? Нужно нажать на аргументе конструктора Alt+Enter, выбрать Create Field for Parameter 'providers'
//
//        3. Если провайдеры не переданы в конструктор контроллера, то брось IllegalArgumentException.
//
//        4. Создай метод toString в классе Controller (Alt+Insert -> toString()) со стандартной реализацией (должен выводить поле providers)
//
//        5. В методе main создай провайдер, а потом создай контроллер с этим провайдером.
//
//        6. В методе main выведи в консоль созданный экземпляр Controller-а.

import com.javarush.test.level28.lesson15.big01.model.Model;


/**
 * Created by Andriana_Yarmoliuk on 11/7/2016.
 */
public class Controller
{
    private Model model;

    public Controller(Model model)
    {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }
    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}
