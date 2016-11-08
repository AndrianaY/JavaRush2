package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        ArrayList<String> strings = new ArrayList<String>();
        while (true)
        {
            String string = scanner.nextLine();
            if(!string.equals("exit"))
                strings.add(string);
            else
                break;
        }
        FileWriter fileWriter = new FileWriter(fileName);
        for (int a = 0; a < strings.size(); a++)
        {
            byte[] byteArray = strings.get(a).getBytes();
            String lineSeparator = System.lineSeparator();
            fileWriter.write(strings.get(a));
            fileWriter.write(lineSeparator);
            fileWriter.flush();
        }
        fileWriter.write("exit");
        fileWriter.close();
    }
}
