package cn.sse.bupt.controller;

import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by hao.yan on 2015/12/7.
 */
@RestController
@RequestMapping("UserService")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @RequestMapping("login")
    public ResultModel login(@RequestParam("username") String username, @RequestParam("password") String password) {
        LOGGER.info("{} try to login", username);
        if (userService.hasRegistered(username)) {
            ResultModel resultModel = ResultModel.failed("用户名已存在");
            return resultModel;
        }
        password = md5PasswordEncoder.encodePassword(password, username);
        if (userService.login(username, password))
            return ResultModel.success();

        ResultModel resultModel = ResultModel.failed("密码错误");
        return resultModel;
    }


}
