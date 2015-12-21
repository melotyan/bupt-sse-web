package cn.sse.bupt.util;

import cn.sse.bupt.common.SessionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hao.yan on 2015/12/21.
 */
public class ServletUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServletUtil.class);

    public static void writeCookie(HttpServletResponse response, String name, String value, int expire, String domain) {
        try {
            Cookie cookie = new Cookie(name, value);
            cookie.setPath("/");
            if (!domain.equals("localhost"))
                cookie.setDomain(domain);
            if (expire > 0)
                cookie.setMaxAge(expire);
            response.addCookie(cookie);
        } catch (Exception e) {
            LOGGER.error("error write login cookie {}:{}, error: {}", name, value, e);
        }
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie cookie = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    c.setMaxAge(0);
                    c.setValue(null);
                    if (domain == null)
                        domain = SessionConstants.DOMAIN;
                    if (!domain.equals("localhost"))
                        c.setDomain(domain);
                    c.setPath("/");
                    cookie = c;
                }
            }
        }
        if (cookie != null)
            response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return cookie;
        }
        return null;
    }

}
