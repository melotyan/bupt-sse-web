package cn.sse.bupt.controller.interceptor;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hao.yan on 2015/12/15.
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private List<String> excludedUrl;
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isExcludeUrl(request))
            return true;
        return this.doPreHandle(request, response, handler);
    }

    protected abstract boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    private boolean isExcludeUrl(HttpServletRequest request) {
        if (CollectionUtils.isEmpty(excludedUrl)) {
            LOGGER.debug("excludeUrl list is empty");
            return false;
        }
        String requestURI = request.getRequestURI();
        for (String url : excludedUrl) {
            if (pathMatcher.match(url, requestURI)) {
                LOGGER.debug("RequestURI:{} is match excludeUrl:{}", requestURI, excludedUrl);
                return true;
            }
        }
        return false;
    }

    public List<String> getExcludedUrl() {
        return excludedUrl;
    }

    public void setExcludedUrl(List<String> excludedUrl) {
        this.excludedUrl = excludedUrl;
    }
}
