package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        String fileName = "";
        Scanner reader = new Scanner(new InputStreamReader(System.in));

            fileName = reader.nextLine();

            File file = new File(fileName);

            ArrayList<Integer> arr = new ArrayList<>();
           Scanner sc = new Scanner(file);

                while (sc.hasNextInt())
                {
                    int value = sc.nextInt();
                    if (value % 2 == 0)
                    {
                        arr.add(value);
                    }
                }



            Collections.sort(arr);
            for (int i : arr)
            {
                System.out.println(i);
            }
        }
    }
