package cn.sse.bupt.controller;

import cn.sse.bupt.enums.AccountStatusEnum;
import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.UserService;
import cn.sse.bupt.util.MailSenderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.management.counter.AbstractCounter;

import java.util.Date;

/**
 * Created by hao.yan on 2015/12/7.
 */
@RestController
@RequestMapping("UserService")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final String HOME_URL = "http://melotyan/egoverment/";
    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderUtil mailSenderUtil;

    @RequestMapping("login")
    public ResultModel login(@RequestParam("username") String username, @RequestParam("password") String password) {
        LOGGER.info("{} try to login", username);
        if (!userService.hasRegistered(username))
            return ResultModel.failed("用户名不存在");
        if (userService.login(username, new Md5PasswordEncoder().encodePassword(password, username)))
            return ResultModel.success();

        return ResultModel.failed("密码错误");
    }

    @RequestMapping("register")
    public ResultModel register(@RequestParam(value="username", required = true) String username, @RequestParam(value="password", required = true) String password, @RequestParam(value="nickname", defaultValue = "") String nickname,
                               @RequestParam(value="email", required=true) String email, @RequestParam(value = "phone", required = true) String phone, @RequestParam("address") String address) {
        if (userService.hasRegistered(username))
        return ResultModel.failed("用户名已存在");

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(new Md5PasswordEncoder().encodePassword(password, username));
        userModel.setNickname(nickname);
        userModel.setEmail(email);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userModel.setUserType(UserTypeEnum.CUSTOMER.getValue());
        userModel.setAccountStatus(AccountStatusEnum.UNACTIVATED.getValue());
        userModel.setCreateTime(new Date());
        long uid = userService.register(userModel);

        String activeUrl = HOME_URL + "UserService/activeAccount/uid/" + uid;
        mailSenderUtil.sendEmail(email, activeUrl);
        return ResultModel.success();
    }

    @RequestMapping("activeAccount")
    public ModelAndView activeAccount(long uid) {
        userService.activeAccount(uid);
        return new ModelAndView("index");
    }



}
