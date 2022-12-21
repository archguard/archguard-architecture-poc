package com.example.api.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.api.contextholder.ArchSystemContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class ArchSystemInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String archSystemId = request.getHeader("ArchSystem-ID");

        ArchSystemContextHolder.setContext(archSystemId);

        log.info("Intercept archSystemId : {}", archSystemId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        ArchSystemContextHolder.removeContext();
        log.info("Clean ArchSystemContextHolder");
    }

}
