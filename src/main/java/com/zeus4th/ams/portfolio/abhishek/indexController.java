package com.zeus4th.ams.portfolio.abhishek;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class indexController {

    @GetMapping ("/abhishek")
    public String index(
    ){
        return "index";
    }

}
