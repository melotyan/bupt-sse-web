package cn.sse.bupt.controller.interceptor;

import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hao.yan on 2015/12/15.
 */
public class BusinessInterceptor extends BaseInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(BusinessInterceptor.class);

    @Override
    public boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserModel userModel = (UserModel) request.getSession().getAttribute(SessionConstants.USER);
        if (userModel == null || userModel.getUserType() == UserTypeEnum.CUSTOMER.getValue()) {
            LOGGER.debug("user is null or user is customer");
            return false;
        }
        return true;
    }
}
