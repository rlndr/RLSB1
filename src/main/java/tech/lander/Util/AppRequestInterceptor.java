package tech.lander.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppRequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AppRequestInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        HttpServletRequest requestCacheWrapperObject
                = new ContentCachingRequestWrapper(request);
        requestCacheWrapperObject.getParameterMap();
        // Read inputStream from requestCacheWrapperObject and log it
        logger.info("Request: " + requestCacheWrapperObject.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        //
    }
}