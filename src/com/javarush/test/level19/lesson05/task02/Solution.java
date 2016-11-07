package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
        String file=in.readLine();
        BufferedReader f = new BufferedReader(new FileReader(file));
        String buffer;
        int count=0;
        while ((buffer=f.readLine())!=null){
            String[] s = buffer.split("[., !?;:-]");
            for (String element: s) {
                if(element.equalsIgnoreCase("world")) count++;
            }
        }
        System.out.println(count);
        in.close();
        f.close();
    }
}