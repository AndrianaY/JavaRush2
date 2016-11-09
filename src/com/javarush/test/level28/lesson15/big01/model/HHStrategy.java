package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriana_Yarmoliuk on 11/7/2016.
 */
public class HHStrategy implements Strategy
{
     private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String referrer = "https://hh.ua/search/vacancy?text=java+Kiev&page=1";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";
    private static final int timeout = 5 * 1000;
    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int nom = 0;
        try
        {
            while (true)
            {
                Document doc = getDocument(searchString, nom++); // получили документ и увеличили счетчик
                Elements elements = (Elements) doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break; // если нет вакансий прекращаю
                for (Element element : elements)
                {
                    // title
                    Element titleElement = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElement.text();
                    // salary
                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null)
                    {
                        salary = salaryElement.text();
                    }
                    // city
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    // company
                    String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").first().text();
                    // site
                    String siteName = "http://hh.ua/";
                    // url
                    String url = titleElement.attr("href");
                    // add vacancy to the list
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException
    {
        String url = String.format(URL_FORMAT, searchString, page);//searchString город, 1 это страница
        try
        {
            Document doc = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).timeout(timeout).get();
            //  String s = doc.html();
            return doc;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}