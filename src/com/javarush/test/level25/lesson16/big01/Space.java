package com.javarush.test.level25.lesson16.big01;
import java.util.ArrayList;

/**
 * Created by Andriana_Yarmoliuk on 10/28/2016.
 */
public class Space
{
    int width;
    int heigh;
    SpaceShip ship;
    ArrayList<Ufo> ufos = new ArrayList<>();
    ArrayList<Rocket> rockets = new ArrayList<>();
    ArrayList<Bomb> bombs = new ArrayList<>();

    public Space(int width, int heigh)
    {
        this.width = width;
        this.heigh = heigh;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeigh()
    {
        return heigh;
    }

    public SpaceShip getShip()
    {
        return ship;
    }

    public void setShip(SpaceShip ship)
    {
        this.ship = ship;
    }

    public ArrayList<Ufo> getUfos()
    {
        return ufos;
    }

    public ArrayList<Rocket> getRockets()
    {
        return rockets;
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }

    public void run(){

    }

    public void draw(){

    }

    public void sleep(int ms){

    }

    public static void main(String[] args)
    {

    }
}
