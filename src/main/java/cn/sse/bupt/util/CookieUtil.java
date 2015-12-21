package cn.sse.bupt.util;

import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.controller.model.UsernamePassword;
import com.alibaba.dubbo.common.utils.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hao.yan on 2015/12/21.
 */
public class CookieUtil {
    private final static String LOGIN_COOKIE_NAME = "login";

    public static void writeLoginCookie(HttpServletResponse response, String username, String password, int expire) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            return;
        String cookieValue = username + "||" + password;
        ServletUtil.writeCookie(response, LOGIN_COOKIE_NAME, cookieValue, expire, SessionConstants.DOMAIN);
    }

    public static void deleteLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        ServletUtil.deleteCookie(request, response, SessionConstants.DOMAIN, LOGIN_COOKIE_NAME);
    }

    public static UsernamePassword getLoginCookie(HttpServletRequest request) {
        Cookie cookie = ServletUtil.getCookie(request, LOGIN_COOKIE_NAME);
        if (cookie == null || StringUtils.isEmpty(cookie.getValue()))
            return null;

        String value = cookie.getValue();
        String[] array = value.split("\\|\\|");
        if (array != null && array.length == 2)
            return new UsernamePassword(array[0], array[1]);
        return null;
    }
}
