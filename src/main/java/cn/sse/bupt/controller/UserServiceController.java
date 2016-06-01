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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResultModel login(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam(value = "rememberMe", defaultValue = "0") int rememberMe, @RequestParam("captcha") String captcha) {
        LOGGER.info("{} try to login", username);
        UserModel userModel = userService.findUserByUsername(username);
        if (!captcha.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            LOGGER.info("captcha is wrong");
            return ResultModel.failed("验证码错误");
        }
        if (userModel == null) {
            LOGGER.info("account {} not exists", username);
            return ResultModel.failed("用户名不存在");
        }
        if (userModel.getAccountStatus() != AccountStatusEnum.ACTIVITATED.getValue()) {
            LOGGER.warn("account {} is not activated", username);
            return ResultModel.failed("当前账户未激活");
        }
        String encodePassword = encode(password, username);
        if (!userModel.getPassword().equals(encodePassword)) {
            LOGGER.warn("account {} login with wrong password", username);
            return ResultModel.failed("密码错误");
        }

        CookieUtil.writeLoginCookie(response, username, encodePassword, rememberMe == 0 ? DEFAULT_EXPIRE : LONG_EXPIRE);
        HttpSession session = request.getSession();
        Object redirectURL = session.getAttribute(SessionConstants.LAST_URL);
        session.setAttribute(SessionConstants.USER, userModel);
        if (redirectURL != null) {
            LOGGER.info("user {} login success, remove last_url session, redirect to page:{}", username, redirectURL);
            session.removeAttribute(SessionConstants.LAST_URL);
            ResultModel resultModel = ResultModel.success();
            resultModel.put("redirect", String.valueOf(redirectURL));
            return resultModel;
        }
        LOGGER.info("user {} login success, redirect to index", username);
        ResultModel resultModel = ResultModel.success();
        resultModel.put("redirect", "/");
        return resultModel;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultModel register(HttpServletRequest request, @RequestParam(value="username", required = true) String username, @RequestParam(value="password", required = true) String password,
                               @RequestParam(value = "repassword", required = true) String repassword, @RequestParam(value="email", required=true) String email, @RequestParam(value = "captcha", required = true) String captcha) {

        if (username.equals("") || password.equals("") || email.equals(""))
            return ResultModel.failed("输入项不能为空");
        if (!captcha.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)))
            return ResultModel.failed("验证码错误");
        if (!password.equals(repassword))
            return ResultModel.failed("两次密码不一致");
        if (userService.hasRegistered(username))
            return ResultModel.failed("用户名已存在");

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(encode(password, username));
        userModel.setNickname(username);
        userModel.setEmail(email);
        userModel.setPhone("");
        userModel.setAddress("");
        userModel.setUserType(UserTypeEnum.CUSTOMER.getValue());
        userModel.setAccountStatus(AccountStatusEnum.UNACTIVATED.getValue());
        userModel.setCreateTime(new Date());

        String sessionId = request.getRequestedSessionId();
        request.getSession().setAttribute(SessionConstants.ACTIVE_URL, userModel.getUsername() + sessionId);
        String activeUrl = "http://" + request.getServerName() + "/userService/activeAccount/" + userModel.getUsername() + "/" + sessionId;
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

    @RequestMapping("activeAccount/{username}/{sessionId}")
    public ModelAndView activeAccount(HttpServletRequest request, @PathVariable String username, @PathVariable String sessionId) {
        String requestId = String.valueOf(request.getSession().getAttribute(SessionConstants.ACTIVE_URL));
        if (request == null || !requestId.equals(username + sessionId)) {
            LOGGER.warn("user {} active failed, new sessionId:{}, old sessionId:{}", username, requestId, username + sessionId);
            return new ModelAndView(REDIRECT + "/");
        }
        UserModel userModel = userService.findUserByUsername(username);
        userService.activeAccount(userModel.getId());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.USER, userModel);
        LOGGER.info("user {} active success", username);
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
        LOGGER.info("user {} personal info updated success", userModel.getUsername());
        return ResultModel.success("修改成功");
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteLoginCookie(request, response);
        request.getSession().removeAttribute(SessionConstants.USER);
        LOGGER.info("logout success, redirect to login page");
        return new ModelAndView(REDIRECT + "/userService/preLogin");
    }

    @RequestMapping("preChangePassword")
    public ModelAndView preChangePassword() {
        return new ModelAndView("user/password");
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public ResultModel changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("repassword") String repassword, @RequestParam("newPassword") String newPassword) {
        if (!newPassword.equals(repassword)) {
            return ResultModel.failed("两次密码不一致");
        }
        UserModel userModel = getLoginUser();
        String encodePassword = encode(oldPassword, userModel.getUsername());
        if (!encodePassword.equals(userModel.getPassword())) {
            LOGGER.info("wrong password");
            return ResultModel.failed("旧密码错误");
        }
        userModel.setPassword(encode(newPassword, userModel.getUsername()));
        userService.updateUserInfo(userModel);
        LOGGER.info("user:{} success change password", userModel.getUsername());
        return ResultModel.success("修改密码成功");

    }

    private String encode(String password, String salt) {
        return new Md5PasswordEncoder().encodePassword(password, salt);
    }

    @RequestMapping("listAllUsers")
    public ModelAndView listAllUsers() {
        if (getLoginUser().getUserType() != UserTypeEnum.ADMIN.getValue()) {
            LOGGER.info("only admin can view all users");
            return new ModelAndView("common/404");
        }
        List<UserModel> userModelList = userService.getAllUsers();
        return new ModelAndView("user/admin", "list", userModelList);

    }

    @RequestMapping("manageUser")
    public ResultModel manageUser(@RequestParam("uid") int uid, @RequestParam("userType") int userType, @RequestParam("accountStatus") int accountStatus) {
        if (getLoginUser().getUserType() != UserTypeEnum.ADMIN.getValue()) {
            LOGGER.info("only admin can manage user");
            return ResultModel.failed("对不起,您没有修改用户的权限");
        }
        userService.manageUser(uid, userType, accountStatus);
        return ResultModel.success("用户修改成功");
    }


}
