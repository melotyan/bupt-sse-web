package cn.sse.bupt.controller.interceptor;

import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.enums.AccountStatusEnum;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hao.yan on 2015/12/14.
 */
public class LoginInterceptor extends BaseInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserModel userModel = (UserModel) request.getSession().getAttribute(SessionConstants.USER);
        if (userModel == null) {
            LOGGER.debug("has not login, direct to login page");
            String lastUrl = String.valueOf(request.getRequestURL());
            LOGGER.debug("after login, redirect to {}", lastUrl);
            request.getSession().setAttribute(SessionConstants.LAST_URL, lastUrl);
            request.getRequestDispatcher("/egovernment/userService/preLogin").forward(request, response);
            return false;
        }
        return true;
    }


}
