package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file_to_write = reader.readLine();
        String file_to_read = reader.readLine();
        FileInputStream infile_wr = new FileInputStream(file_to_write);
        FileInputStream infile_read = new FileInputStream(file_to_read);
        byte[] f1 = new byte[infile_wr.available()];
        while (infile_wr.available() > 0) {
            infile_wr.read(f1);
        }
        byte[] f2 = new byte[infile_read.available()];
        while (infile_read.available() > 0) {
            infile_read.read(f2);
        }
        FileOutputStream infile_write1 = new FileOutputStream(file_to_write);
        infile_write1.write(f2);
        FileOutputStream infile_write2 = new FileOutputStream(file_to_write, true);
        infile_write2.write(f1);
        reader.close();
        infile_wr.close();
        infile_read.close();
        infile_write1.close();
        infile_write2.close();
    }
}