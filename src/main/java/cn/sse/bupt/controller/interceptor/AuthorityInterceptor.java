package cn.sse.bupt.controller.interceptor;

import cn.sse.bupt.common.SessionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hao.yan on 2015/12/15.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer uid = (Integer) request.getSession().getAttribute(SessionConstants.USER_ID);
        Integer accountStatus = (Integer) request.getSession().getAttribute(SessionConstants.ACCOUNT_STATUS);
        String password = (String) request.getSession().getAttribute(SessionConstants.PASSWORD);

        return true;
    }
}
