package kr.co.toptoptop.demo.web.controller;

import kr.co.toptoptop.demo.web.model.Book;
import kr.co.toptoptop.demo.web.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiRestController {

    private final CrawlingService crawlingService;

    @GetMapping("/getBookList")
    public HashMap<String, Object> getList() {
        log.info("############# getList");
        Elements element = crawlingService.joupParser("http://www.yes24.com/24/Category/Display/001001003022", ".cCont_goodsSet");

        List<Book> books = new ArrayList<>();
        for(Element el: element) {
            String goodsImg = el.select(".goods_imgSet img").attr("src");
            String goodsName = el.select(".goods_name a").eq(0).text();
            String goodsAuth = el.select(".goods_auth a").text() + " 저";
            String goodsPub = el.select(".goods_pub").text();
            String goodsDate = el.select(".goods_date").text();
            String goodsPrice = el.select(".goods_price em").eq(0).text();

            Book param = Book
                    .builder()
                    .goodsImg(goodsImg)
                    .goodsName(goodsName)
                    .goodsAuth(goodsAuth)
                    .goodsPub(goodsPub)
                    .goodsDate(goodsDate)
                    .goodsPrice(goodsPrice)
                    .build();

            books.add(param);
        }

        log.info(books.toString());
        HashMap<String, Object> result = new HashMap<>();
        result.put("books", books);
        return result;
    }
}
