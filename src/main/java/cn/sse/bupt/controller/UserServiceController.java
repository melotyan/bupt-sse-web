package cn.sse.bupt.controller;

import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.enums.AccountStatusEnum;
import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.UserService;
import cn.sse.bupt.util.CookieUtil;
import cn.sse.bupt.util.MailSenderUtil;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hao.yan on 2015/12/7.
 */
@RestController
@RequestMapping("userService")
public class UserServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceController.class);
    private final String HOME_URL = "http://egovernment.melotyan.com";
    private final int DEFAULT_EXPIRE = 15 * 60;
    private final int LONG_EXPIRE = Integer.MAX_VALUE;
    private final static String REDIRECT = "redirect:";

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
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam(value = "rememberMe", defaultValue = "0") int rememberMe, @RequestParam("captcha") String captcha) {
        LOGGER.info("{} try to login", username);
        UserModel userModel = userService.findUserByUsername(username);
        if (!captcha.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            LOGGER.info("captcha is wrong");
            return new ModelAndView("user/login", "msg", "验证码错误");
        }
        if (userModel == null) {
            LOGGER.info("account {} not exists", username);
            return new ModelAndView("user/login", "msg", "用户名不存在");
        }
        if (userModel.getAccountStatus() != AccountStatusEnum.ACTIVITATED.getValue()) {
            LOGGER.warn("account {} is not activated", username);
            return new ModelAndView("user/login", "msg", "当前账户未激活");
        }
        String encodePassword = new Md5PasswordEncoder().encodePassword(password, username);
        if (!userModel.getPassword().equals(encodePassword)) {
            LOGGER.warn("account {} login with wrong password", username);
            return new ModelAndView("user/login", "msg", "密码错误");
        }

        CookieUtil.writeLoginCookie(response, username, encodePassword, rememberMe == 0 ? DEFAULT_EXPIRE : LONG_EXPIRE);
        HttpSession session = request.getSession();
        Object redirectURL = session.getAttribute(SessionConstants.LAST_URL);
        session.setAttribute(SessionConstants.USER, userModel);
        if (redirectURL != null) {
            LOGGER.info("user {} login success, remove last_url session, redirect to page:{}", username, redirectURL);
            session.removeAttribute(SessionConstants.LAST_URL);
            return new ModelAndView(REDIRECT + redirectURL);
        }
        LOGGER.info("user {} login success, redirect to index", username);
        return new ModelAndView(REDIRECT + "/");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultModel register(HttpServletRequest request, @RequestParam(value="username", required = true) String username, @RequestParam(value="password", required = true) String password,
                               @RequestParam(value = "repassword", required = true) String repassword, @RequestParam(value="email", required=true) String email, @RequestParam(value = "captcha", required = true) String captcha) {

        if (!captcha.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)))
            return ResultModel.failed("验证码错误");
        if (!password.equals(repassword))
            return ResultModel.failed("两次密码不一致");
        if (userService.hasRegistered(username))
            return ResultModel.failed("用户名已存在");

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(new Md5PasswordEncoder().encodePassword(password, username));
        userModel.setNickname(username);
        userModel.setEmail(email);
        userModel.setPhone("");
        userModel.setAddress("");
        userModel.setUserType(UserTypeEnum.CUSTOMER.getValue());
        userModel.setAccountStatus(AccountStatusEnum.UNACTIVATED.getValue());
        userModel.setCreateTime(new Date());

        LOGGER.info("userId {} encode password", userModel.getId());
        String sessionId = request.getRequestedSessionId();
        request.getSession().setAttribute(SessionConstants.USER_ID, userModel.getId() + sessionId);
        String activeUrl = HOME_URL + "/userService/activeAccount/" + userModel.getId() + "/" + sessionId;
        try {
            mailSenderUtil.sendEmail(email, activeUrl);
            userService.register(userModel);
            LOGGER.info("send active email to userID:{}", userModel.getId());
        } catch (org.springframework.mail.MailSendException e) {
            LOGGER.error(e.toString());
            return ResultModel.failed("邮箱地址有误");
        }
        return ResultModel.success("请去邮箱确认");
    }

    @RequestMapping("activeAccount/{uid}/{sessionId}")
    public ModelAndView activeAccount(HttpServletRequest request, @PathVariable Integer uid, @PathVariable String sessionId) {
        String requestId = String.valueOf(request.getSession().getAttribute(SessionConstants.USER_ID));
        if (request == null || !requestId.equals(uid + sessionId)) {
            LOGGER.warn("account active failed, new sessionId:{}, old sessionId:{}", requestId, uid + sessionId);
            return new ModelAndView(REDIRECT + "/");
        }
        userService.activeAccount(uid);
        UserModel userModel = userService.findUserById(uid);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.USER, userModel);
        LOGGER.info("userId:{} active success", uid);
        return new ModelAndView(REDIRECT + "/");
    }

    @RequestMapping(value = "getPersonalInfo")
    public ModelAndView getPersonalInfo() {
        return new ModelAndView("user/user_detail");
    }

    @RequestMapping(value = "editPersonalInfo", method = RequestMethod.POST)
    public ResultModel editPersonalInfo(@RequestParam("nickname") String nickname,
                                        @RequestParam("phone") String phone, @RequestParam("address") String address) {
        UserModel userModel = getLoginUser();
        userModel.setNickname(nickname);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userService.updateUserInfo(userModel);
        LOGGER.info("user {} personal info updated success", userModel.getId());
        return ResultModel.success();
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteLoginCookie(request, response);
        request.getSession().removeAttribute(SessionConstants.USER);
        LOGGER.info("logout success, redirect to login page");
        return new ModelAndView(REDIRECT + "/userService/preLogin");
    }


}
