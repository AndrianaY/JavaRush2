package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream r = new FileInputStream(args[0]);
        double difference = 0;
        byte space = " ".getBytes()[0];
        int spaces = 0;
        byte[] bytes = new byte[r.available()];
        int total = r.read(bytes);
        for(byte read : bytes){
            if(read == space)
            {
                spaces++;
            }

        }
        difference = (double)spaces/(double)total*100;
        System.out.println(String.format("%.2f", difference));
        r.close();
    }
}