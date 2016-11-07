package com.javarush.test.level27.lesson15.big01;
import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
//3. Метод void update(Observable observable, Object arg), который необходимо реализовать, принимает два параметра.
//        -observable - объект, который отправил нам значение
//        -arg - само значение, в нашем случае - это объект Order
//        На данном этапе мы сымитируем обработку и выведем в консоль "Start cooking - " + order
/**
 * Created by Andriana_Yarmoliuk on 11/3/2016.
 */
public class Tablet extends Observable
{
    public int getNumber()
    {
        return number;
    }

    private final int number;
    public static Logger logger = Logger.getLogger(Tablet.class.getName());


    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

    public Tablet(int number)
    {
        this.number = number;

    }

    public void createOrder(){
        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty()){
                setChanged();
                notifyObservers(order);
                try {
                    AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                    manager.processVideos();
                } catch (NoVideoAvailableException e) {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

}
