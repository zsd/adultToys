package com.zsd.security.authenticate;

import com.zsd.comm.context.SecurityContextHolder;
import com.zsd.comm.context.SystemContextHolder;
import com.zsd.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份认证拦截器
 */
public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticateInterceptor.class);

    private String[] excludes; // 拦截器排除的请求集合

    public AuthenticateInterceptor(String[] excludes) {
        this.excludes = excludes;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object userObject) throws Exception {
        boolean result = false;

        String ctx = request.getContextPath();
        String uri = request.getRequestURI();
        // 判断拦截器排除的请求集合中是否包含当前请求
        if (excludes != null) {
            for (String exclude : excludes) {
                // 如果包含当前请求, 则不拦截
                if (uri.startsWith(ctx + exclude)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                logger.debug("身份认证拦截器排除的请求: {}", uri);
                return result;
            }
        }

        User user = SecurityContextHolder.getUser();
        if (user == null) {
            result = false;
            response.sendRedirect(ctx + SystemContextHolder.getLogin());
            return result;
        }

        return true;
    }

}
