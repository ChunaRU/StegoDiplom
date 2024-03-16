package com.example.stegodiplomagui.Search;

import com.example.stegodiplomagui.digital_electronic_signature.DES;
import com.example.stegodiplomagui.stego.Coding;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

public class GoogleSearch {
    public static List<String> getLinks(String search,String requiredSearch, String format) throws IOException {

        out.println("Поисковое слово: " + search);
        out.println("Формат: " + format);
        String url = "https://www.google.com/search?q=" + search + "+filetype%3A" + format + "&sca_esv=583632294&sxsrf=AM9HkKn4HPwoEJjgyCAJyeZFYoKiftbt5g%3A1700320594520&ei=UtVYZZqtH_TQi-gP5YG-wA8&ved=0ahUKEwianqno682CAxV06AIHHeWAD_gQ4dUDCBA&uact=5&oq=%D1%83%D0%BC%D0%BA+filetype%3A%D0%94%D0%9E%D0%9A&gs_lp=Egxnd3Mtd2l6LXNlcnAiFtGD0LzQuiBmaWxldHlwZTrQlNCe0JpIi0xQqg9YyUZwCXgAkAEAmAGHCKAByBKqAQkwLjMuNC43LTG4AQPIAQD4AQHCAgQQIxgn4gMEGAEgQYgGAQ&sclient=gws-wiz-serp";

        int num;
        int step = 1;
        int trash = 7;

        List<String> linkList = new ArrayList<>();
        do {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            Elements links = doc.body().select("a[href]");
            num = links.size() - (15 + trash);

            out.println("----- Страница: " + step + " ----- Количество ссылок: " + num +  " -----");


            for(int j = 13, k = 0; j < links.size() - trash; j++,k++) { //Убираем лишние гугловские ссылки
                Element link = links.get(j);
                linkList.add(link.attr("abs:href"));



                out.print(link.attr("abs:href"));
                out.print("  ");
                out.println("(" + link.text() + ")");
            }
            url = links.get(links.size()-7).attr("abs:href");
            step++;
            if(step == 2 || step == 3) trash ++;
        } while(num > 9);
        out.println("Общее количество ссылок: " + linkList.size());
        return linkList;


    }

    public static File download(String strURL, String format) {
        try {
            URL url = new URL(strURL);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, new File("tmp" + "." + "doc").toPath());

        } catch (IOException ex) {
            err.println(ex.getMessage());
            out.println();
        }

        return new File( "tmp" + "." + format);
    }

    public static void delete(File file){
        file.delete();
    }





}