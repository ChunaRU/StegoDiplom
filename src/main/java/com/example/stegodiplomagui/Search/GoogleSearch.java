package com.example.stegodiplomagui.Search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleSearch {
    public static List<String> getLinks(String search,String requiredSearch, String format) throws IOException {

        List<String> linkList = new ArrayList<String>();
        System.out.println("Поисковое слово: " + search);
        System.out.println("Формат: " + format);
        String url = "https://www.google.com/search?q=" + search + "+filetype%3A" + format + "&sca_esv=583632294&sxsrf=AM9HkKn4HPwoEJjgyCAJyeZFYoKiftbt5g%3A1700320594520&ei=UtVYZZqtH_TQi-gP5YG-wA8&ved=0ahUKEwianqno682CAxV06AIHHeWAD_gQ4dUDCBA&uact=5&oq=%D1%83%D0%BC%D0%BA+filetype%3A%D0%94%D0%9E%D0%9A&gs_lp=Egxnd3Mtd2l6LXNlcnAiFtGD0LzQuiBmaWxldHlwZTrQlNCe0JpIi0xQqg9YyUZwCXgAkAEAmAGHCKAByBKqAQkwLjMuNC43LTG4AQPIAQD4AQHCAgQQIxgn4gMEGAEgQYgGAQ&sclient=gws-wiz-serp";

        int num = 0;
        int step = 1;
        int trash = 7;

        do {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            Elements links = doc.body().select("a[href]");
            num = links.size() - (15 + trash);

            System.out.println("----- Страница: " + step + " ----- Количество ссылок: " + num +  " -----");


            for(int j = 13, k = 0; j < links.size() - trash; j++,k++) { //Убираем лишние гугловские ссылки
                Element link = links.get(j);
                linkList.add(link.attr("abs:href"));



                System.out.print(link.attr("abs:href"));
                System.out.print("  ");
                System.out.println("(" + link.text() + ")");
            }
            url = links.get(links.size()-7).attr("abs:href");
            step++;
            if(step == 2 || step == 3) trash ++;
        } while(num > 9);
        System.out.println("Общее количество ссылок: " + linkList.size());
        return linkList;

    }
}