package kr.co.toptoptop.demo.web.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CrawlingService {

    public Elements joupParser(String targetUrl, String cssQuery) {
//        String url = "http://www.yes24.com/24/Category/Display/001001003022";
        Document doc = null;

        try {
            doc = Jsoup.connect(targetUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Elements element = doc.select(".category--body--main article");
        Elements element = doc.select(cssQuery);

        return element;
    }
}
