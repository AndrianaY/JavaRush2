package com.javarush.test.level25.lesson16.big01;
//а) Добавить в класс две переменные width и height
//        б) Добавить в класс переменную matrix (char[][])
//        в) Добавь геттеры для них
//        г) В конструкторе проинициализируй матрицу

/**
 * Created by Andriana_Yarmoliuk on 10/28/2016.
 */
public class Canvas
{
    int width;
    int height;
    char[][] matrix;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        matrix = new char[width][height];
    }

    public char[][] getMatrix()
    {
        return matrix;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }


}
