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
 * Created by Andriana_Yarmoliuk on 11/9/2016.
 */
public class MoikrugStrategy implements Strategy
{ private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    //private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";
//    private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html/search/vacancy?text=java+%s&page=%d";
    private static final String referrer = "https://moikrug.ru/vacancies?q=java+Dnepropetrovsk";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";
    private static final int timeout = 5 * 1000;
    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        try
        {
            while (true)
            {
                Document doc = getDocument(searchString, page++);
//                Elements vacancyElements = doc.select("div.job");
                Elements vacancyElements = doc.getElementsByClass("job");
                if (vacancyElements.size() > 0)
                {
                    for (Element element : vacancyElements)
                    {
                        Vacancy v = new Vacancy();
//                        String vTitle = element.select("div.title").select("a").text();
                        String vTitle = element.getElementsByClass("title").select("a").text();
                        v.setTitle(vTitle);
//                        String vSalary = element.select("div.count").text();
                        String vSalary = element.getElementsByClass("count").text();
                        v.setSalary(vSalary);
//                        String vCity = element.select("span.location").select("a").text();
                        String vCity = element.getElementsByClass("location").text();
                        v.setCity(vCity);
//                        String vCompanyName = element.select("div.company_name").select("a").text();
                        String vCompanyName = element.getElementsByClass("company_name").select("a[href]").text();
                        v.setCompanyName(vCompanyName);
                        v.setSiteName("https://moikrug.ru");
//                        String vURL = element.select("div.title").select("a").attr("abs:href");
                        String vURL = element.getElementsByClass("title").select("a").attr("abs:href");
                        v.setUrl(vURL);
                        vacancies.add(v);
                    }
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return vacancies;
    }
    protected Document getDocument(String searchString, int page) throws IOException
    {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent(userAgent)
                .referrer(referrer)
                .get();
        return document;
    }
}
