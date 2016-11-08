package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;
/**
 * Created by Andriana_Yarmoliuk on 11/8/2016.
 */
public class HtmlView implements View {
    //    ./src/com/javarush/test/level28/lesson15/big01/view/vacancies.html
    private final String filePath =
            "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/")
                    + "/vacancies.html";
    private Controller controller;

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "utf-8");
    }

    private void updateFile(String updatedFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(updatedFile);
        writer.close();

    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) throws IOException {

        Document document = getDocument();
        Element tempElement = document.select("[class*=\"template\"]").first();
//            System.out.println("element: \n" + element);

        //getting pattern Element for new vacancies
        Element pattern = tempElement.clone();
        pattern.removeAttr("style");
        pattern.removeClass("template");
//            System.out.println("pattern: \n" + pattern);

        //remove all tags <tr> with only one class="vacancy"
        document.select("tr[class=\"vacancy\"]").remove();
//            System.out.println("document after remove <tr> " +
//                    "tags with class=\"vacancy\"\n" + document);

        //adding new Vacancies to result page updatedFileContent
        for (Vacancy vacancy : vacancyList) {
            Element newVacancyElement = pattern.clone();
            newVacancyElement.select("[class=\"city\"]").
                    first().text(vacancy.getCity());
            newVacancyElement.select("[class=\"companyName\"]").
                    first().text(vacancy.getCompanyName());
            newVacancyElement.select("[class=\"salary\"]").
                    first().text(vacancy.getSalary());
            Element link = newVacancyElement.select("a").first();
            link.text(vacancy.getTitle());
            link.attr("href", vacancy.getUrl());
            tempElement.before(newVacancyElement.outerHtml());
        }
//            System.out.println("document after adding new Vacancies:\n" + document);

        return document.outerHtml();
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies) {
//        System.out.println(vacancies.size());
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}