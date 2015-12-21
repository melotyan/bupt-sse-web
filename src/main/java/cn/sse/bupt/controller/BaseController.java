package cn.sse.bupt.controller;

import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hao.yan on 2015/12/21.
 */
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    protected UserModel getLoginUser() {
        UserModel userModel = (UserModel) getHttpRequest().getSession().getAttribute(SessionConstants.USER);
        if (userModel == null)
            LOGGER.warn("can not get user model in reqeust");
        return userModel;
    }

    protected HttpServletRequest getHttpRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            LOGGER.error("get servlet request failed, with exception:{}", e);
        }
        return request;
    }
}
