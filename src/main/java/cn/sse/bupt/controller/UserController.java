package cn.sse.bupt.controller;

import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.enums.AccountStatusEnum;
import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.UserService;
import cn.sse.bupt.util.MailSenderUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hao.yan on 2015/12/7.
 */
@RestController
@RequestMapping("userService")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final String HOME_URL = "http://www.melotyan.com/egoverment/";
    private Gson gson = new Gson();
    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderUtil mailSenderUtil;

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, @RequestParam(value="redirectUrl", defaultValue = "index") String redirectUrl,
                              @RequestParam("username") String username, @RequestParam("password") String password) {
        LOGGER.info("{} try to login, password:{}", username, password);
        UserModel userModel = userService.findUserByUsername(username);

        if (userModel == null) {
            LOGGER.info("account {} not exists", username);
            return new ModelAndView("user/login", "msg", "用户名不存在");
        }
        if (userModel.getAccountStatus() != AccountStatusEnum.ACTIVITATED.getValue()) {
            LOGGER.info("account {} is not activated", username);
            return new ModelAndView("user/login", "msg", "当前账户未激活");
        }
        if (!userModel.getPassword().equals(new Md5PasswordEncoder().encodePassword(password, username))) {
            LOGGER.info("account {} password wrong");
            return new ModelAndView("user/login", "msg", "密码错误");
        }

        ModelAndView modelAndView = new ModelAndView(redirectUrl);
        modelAndView.addObject("userModel", userModel);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.USER_ID, userModel.getId());
        session.setAttribute(SessionConstants.PASSWORD, password);
        session.setAttribute(SessionConstants.USERNAME, username);

        LOGGER.info("login success:{}", gson.toJson(userModel));
        return modelAndView;
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

        String activeUrl = HOME_URL + "UserService/activeAccount/" + uid;
        mailSenderUtil.sendEmail(email, activeUrl);
        return ResultModel.success();
    }

    @RequestMapping("activeAccount/{uid}")
    public ModelAndView activeAccount(@PathVariable Integer uid) {
        userService.activeAccount(uid);
        return new ModelAndView("/index");
    }

    @RequestMapping("editPersonalInfo/{uid}")
    public ModelAndView editPersonalInfo(HttpServletRequest request, @PathVariable Integer uid, @RequestParam(value="nickname", defaultValue = "") String nickname,
                                         @RequestParam(value = "phone", required = true) String phone, @RequestParam("address") String address) {
        if (request.getSession().getAttribute(SessionConstants.USER_ID) != uid) {
            LOGGER.info("editPersonalInfo failed");
            new ModelAndView("user/login", "error", "没有权限");
        }
        UserModel userModel = userService.findUserById(uid);
        if (userModel == null) {
            LOGGER.info("no such userModel with id:{}", uid);
            return new ModelAndView("user/login", "error", "没有对应的用户");
        }
        userModel.setNickname(nickname);
        userModel.setAddress(address);
        userModel.setPhone(phone);
        userService.updateUserInfo(userModel);
        return new ModelAndView("user/personalInfo");
    }



}
