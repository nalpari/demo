package kr.co.toptoptop.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/handlebars")
    public String inde2() {
        return "index2";
    }
}