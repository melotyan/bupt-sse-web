package cn.sse.bupt.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hao.yan on 2015/12/17.
 */
public class CustomerMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerMappingExceptionResolver.class);
    //需要打印异常日志的页面
    private Set<String> viewSet = new HashSet<String>();

    @Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
        if (modelAndView == null || needLog(modelAndView)) {
            LOGGER.error("failed to run {}, request url is {}, error is {}", handler, getUrlPath(request), ex);
        } else {
            LOGGER.warn("failed to run {}, request url is {}, error is {}", handler, getUrlPath(request), ex == null ? "" : ex.toString());
        }
        return modelAndView;
    }

    private boolean needLog(ModelAndView modelAndView) {
        return modelAndView != null && viewSet.contains(modelAndView.getViewName());
    }

    private String getUrlPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (servletPath == null || servletPath.equals("/"))
            servletPath = "";
        return servletPath + request.getPathInfo();
    }

    public Set<String> getViewSet() {
        return viewSet;
    }

    public void setViewSet(Set<String> viewSet) {
        this.viewSet = viewSet;
    }
}
