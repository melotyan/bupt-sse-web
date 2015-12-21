package cn.sse.bupt.controller;

import cn.sse.bupt.common.SessionConstants;
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
import org.springframework.web.bind.annotation.*;
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
    private final String HOME_URL = "http://www.melotyan.com";
    private final String REDIRECT = "redirect:";

    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderUtil mailSenderUtil;

    @RequestMapping(value = "preLogin")
    public ModelAndView preLogin() {
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "preRegister")
    public ModelAndView preRegister() {
        return new ModelAndView("user/register");
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                              @RequestParam("username") String username, @RequestParam("password") String password) {
        LOGGER.info("{} try to login", username);
        UserModel userModel = userService.findUserByUsername(username);

        if (userModel == null) {
            LOGGER.warn("account {} not exists", username);
            return new ModelAndView("user/login", "msg", "用户名不存在");
        }
        if (userModel.getAccountStatus() != AccountStatusEnum.ACTIVITATED.getValue()) {
            LOGGER.warn("account {} is not activated", username);
            return new ModelAndView("user/login", "msg", "当前账户未激活");
        }
        if (!userModel.getPassword().equals(new Md5PasswordEncoder().encodePassword(password, username))) {
            LOGGER.warn("account {} login with wrong password", username);
            return new ModelAndView("user/login", "msg", "密码错误");
        }

        HttpSession session = request.getSession();
        Object redirectURL = session.getAttribute(SessionConstants.LAST_URL);
        session.setAttribute(SessionConstants.USER, userModel);
        if (redirectURL != null) {
            LOGGER.info("user {} login success, redirect to page:{}", username, redirectURL);
            return new ModelAndView(REDIRECT + redirectURL);
        }
        LOGGER.info("user {} login success, redirect to index");
        return new ModelAndView(REDIRECT + "/");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultModel register(HttpServletRequest request, @RequestParam(value="username", required = true) String username, @RequestParam(value="password", required = true) String password, @RequestParam(value="nickname", defaultValue = "") String nickname,
                               @RequestParam(value="email", required=true) String email, @RequestParam(value = "phone", defaultValue = "") String phone, @RequestParam(value = "address", defaultValue = "") String address) {
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
        userService.register(userModel);

        LOGGER.info("userId {} encode password", userModel.getId());
        String sessionId = request.getRequestedSessionId();
        request.getSession().setAttribute(SessionConstants.USER_ID, userModel.getId() + sessionId);
        String activeUrl = HOME_URL + "/egovernment/userService/activeAccount/" + userModel.getId() + "/" + sessionId;
        mailSenderUtil.sendEmail(email, activeUrl);
        LOGGER.info("send active email to userID:{}", userModel.getId());
        return ResultModel.success();
    }

    @RequestMapping("activeAccount/{uid}/{sessionId}")
    public ModelAndView activeAccount(HttpServletRequest request, @PathVariable Integer uid, @PathVariable String sessionId) {
        String requestId = String.valueOf(request.getSession().getAttribute(SessionConstants.USER_ID));
        if (request == null || !requestId.equals(uid + sessionId)) {
            LOGGER.warn("account active failed, new sessionId:{}, old sessionId:{}", requestId, uid + sessionId);
            return new ModelAndView("index");
        }
        userService.activeAccount(uid);
        UserModel userModel = userService.findUserById(uid);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.USER, userModel);
        LOGGER.info("userId:{} active success", uid);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "getPersonalInfo")
    public ModelAndView getPersonalInfo() {
        return new ModelAndView("user/user_detail");
    }

    @RequestMapping(value = "editPersonalInfo", method = RequestMethod.POST)
    public ResultModel editPersonalInfo(@RequestParam("uid") Integer uid, @RequestParam("nickname") String nickname,
                                        @RequestParam("phone") String phone, @RequestParam("address") String address) {
        UserModel userModel = userService.findUserById(uid);
        if (userModel == null) {
            LOGGER.warn("user is null, with userID:{}", uid);
            return ResultModel.failed("用户不存在");
        }
        userModel.setNickname(nickname);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userService.updateUserInfo(userModel);
        LOGGER.info("user {} personal info updated success", uid);
        return ResultModel.success();
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionConstants.USER);
    }



}
