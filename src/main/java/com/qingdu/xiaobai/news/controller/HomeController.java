package com.qingdu.xiaobai.news.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : KoreQ
 * Date : 2019/12/02
 * Description :
 */
@RestController
public class HomeController {

    //    @RestController 相当于 @Controller 和 @ResponseBody
    @RequestMapping("/home")
    public String hello() {
        return "{\"name\": \"秋月孝雄\", \"age\": 15}";
    }
}
