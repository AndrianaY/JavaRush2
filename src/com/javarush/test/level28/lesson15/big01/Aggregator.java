package com.javarush.test.level28.lesson15.big01;


import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;
import com.javarush.test.level28.lesson15.big01.view.View;

/**
 * Created by Andriana_Yarmoliuk on 11/7/2016.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        HtmlView htmlView = new HtmlView();
        Provider provider = new Provider(new HHStrategy());
        Provider providerKrug = new Provider(new MoikrugStrategy());
        Provider[] providers = new Provider[2];
        providers[0] = provider;
        providers[1] = providerKrug;
        Model model = new Model(htmlView,providers);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}
