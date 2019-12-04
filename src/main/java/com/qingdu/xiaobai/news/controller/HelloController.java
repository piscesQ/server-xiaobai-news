package com.qingdu.xiaobai.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author : KoreQ
 * Date : 2019/12/02
 * Description :
 */
@Controller
public class HelloController {

    //    @RestController 相当于 @Controller 和 @ResponseBody
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        String name = "宫水三叶";
        int age = 19;

        String bgUrl = "http://default.jpg";
        String mainTitle = "MainTitle";
        String subTitle = "SubTitle";
        String welcome = "Welcome";

        String retFormat = "{\"name\":\"%s\",\"age\":%d,\"bgUrl\":\"%s\",\"mainTitle\":\"%s\",\"subTitle\":\"%s\",\"welcome\":\"%s\"}";
        return String.format(retFormat, name, age, bgUrl, mainTitle, subTitle, welcome);
    }

    public static void main(String[] args) {
        HelloController controller = new HelloController();
        System.out.println(controller.hello());
    }
}
