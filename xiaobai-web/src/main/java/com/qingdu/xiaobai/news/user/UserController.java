package com.qingdu.xiaobai.news.user;

import com.qingdu.xiaobai.news.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: YanBin
 * date: 2019-12-06
 * desc:
 */
@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public R list() {
        try {
            R ok = R.isOk();
            ok.setMsg("请求成功！~");
            return ok.data(userService.list());
        } catch (Exception e) {
            return R.isFail(e);
        }
    }
}
