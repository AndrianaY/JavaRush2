package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа одного рекламного ролика
//        в копейках. Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки
//        Используйте метод Collections.sort
//        (Будет тестироваться вместе со следующим заданием)
//        Пример для заказа [Water]:
//        First Video is displaying... 50, 277
//        где First Video - название рекламного ролика
//        где 50 - стоимость показа одного рекламного ролика в копейках
//        где 277 - стоимость показа одной секунды рекламного ролика в тысячных частях копейки (равно 0.277 коп)
//        Используйте методы из класса Advertisement.
//        2.5. В классе Advertisement создайте метод void revalidate(). Этот метод должен:
//        2.5.1. кидать UnsupportedOperationException, если количество показов не положительное число
//        2.5.2. уменьшать количество показов
//        2.6. Для каждого показанного рекламного ролика пересчитать его данные вызвав метод revalidate() у объекта класса Advertisement.

/**
 * Created by Andriana_Yarmoliuk on 11/4/2016.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage;
    private int timeSeconds;
    public AdvertisementManager(int timeInSec)
    {
        this.storage = AdvertisementStorage.getInstance();
        this.timeSeconds = timeInSec;
    }
    public void processVideos() throws NoVideoAvailableException
    {
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
            }
        });
        List<Advertisement> advertisements = new ArrayList<>();
        int timeLeft = timeSeconds;
        for (Advertisement item : storage.list()){
            if (item.getDuration() <= timeLeft) {
                advertisements.add(item);
                timeLeft -= item.getDuration();
            }
        }
        if (advertisements.isEmpty() || timeLeft == timeSeconds)
            throw new NoVideoAvailableException();
        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getAmountPerOneDisplaying() == o2.getAmountPerOneDisplaying()){
                    long oneSecondCost1 = (o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration());
                    long oneSecondCost2 = (o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                    return Long.compare(oneSecondCost1, oneSecondCost2);
                }
                return Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
            }
        });
        for (Advertisement advertisement : advertisements){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    advertisement.getName(),
                    advertisement.getAmountPerOneDisplaying(),
                    advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration()));
            advertisement.revalidate();
        }
    }
}
