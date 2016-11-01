package com.javarush.test.level08.lesson08.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<>();
        for (int i =0; i < 10; i++){
            map.put("surname" + i, "name" + i);
        }
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {

        ArrayList<String> itemsToRemove = new ArrayList<>();
        Iterator<Map.Entry<String, String>> it;

        for (Map.Entry<String, String> pair :map.entrySet())
        {
            it = map.entrySet().iterator();

            while(it.hasNext())
            {
                Map.Entry<String, String> pairToCompare = it.next();
                if (pair.getValue().equals(pairToCompare.getValue()) && !(pair.getKey().equals(pairToCompare.getKey())))
                {
                    itemsToRemove.add(pairToCompare.getValue());
                }

            }
        }
        for (String s: itemsToRemove)
        {
            removeItemFromMapByValue(map, s);
        }
        //напишите тут ваш код

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args)
    {
        HashMap<String, String> names = createMap();
        System.out.println(names);
        names.put("surname10", "name5");
        System.out.println(names);
        removeTheFirstNameDuplicates(names);
        System.out.println(names);
    }
}
