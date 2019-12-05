package com.qingdu.xiaobai.news.controller;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : KoreQ
 * Date : 2019/12/02
 * Description :
 */
@Slf4j
@RestController
public class LombokController {

    //    @RestController 相当于 @Controller 和 @ResponseBody
    @RequestMapping("/lombok")
    public String hello(String date) {
        log.debug("date = " + date);            // lombok log
        return "{\"name\": \"秋月孝雄\", \"age\": 15, \"date\": \"" + date + "\"}";
    }

    private void lombokTest(@NonNull int param) {
//        @Cleanup String name = "Time";
//        log.debug("name" + name);
    }
}
