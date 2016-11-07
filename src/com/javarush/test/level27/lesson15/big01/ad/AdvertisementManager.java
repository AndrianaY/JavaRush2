package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.*;
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
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    int timeSeconds;
    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos() throws NoVideoAvailableException
    {
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                if (result != 0) return -result;
                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();
                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });
        List<Advertisement> selected = selectAd(new ArrayList<Advertisement>(),0,0);
        if (selected.isEmpty()) throw new NoVideoAvailableException();
        for (Advertisement advertisement : selected) {
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
            advertisement.revalidate();
        }
    }
    private List<Advertisement> selectAd(List<Advertisement> current, int length, int step) {
        if (length == timeSeconds || step == storage.list().size()) {
            return current;
        }
        List<Advertisement> var1 = new ArrayList<>(current);
        var1 = selectAd(var1,length,step + 1);
        Advertisement ad = storage.list().get(step);
        int newLength = length + ad.getDuration();
        if (newLength < timeSeconds && ad.getHits() > 0) {
            List<Advertisement> var2 = new ArrayList<>(current);
            var2.add(storage.list().get(step));
            var2 = selectAd(var2, newLength, step + 1);
            int total1 = 0;
            int total2 = 0;
            int length1 = 0;
            int length2 = 0;
            for (Advertisement adv : var1) {
                total1 += adv.getAmountPerOneDisplaying();
                length1 += adv.getDuration();
            }
            for (Advertisement adv : var2) {
                total2 += adv.getAmountPerOneDisplaying();
                length2 += adv.getDuration();
            }
            if (total1 == total2) {
                if (length1 == length2) {
                    return var1.size() < var2.size() ? var1 : var2;
                } else {
                    return length1 > length2 ? var1 : var2;
                }
            } else {
                return total1 > total2 ? var1 : var2;
            }
        } else {
            return var1;
        }
    }
}