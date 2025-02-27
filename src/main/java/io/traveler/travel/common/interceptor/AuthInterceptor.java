package io.traveler.travel.common.interceptor;

import io.traveler.travel.common.exception.AuthorizedException;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        switch (uri) {
            //이런 uri들 객체로 만들어서 관리할지
            case "/trip" -> isAuthorized(session);
        }


        return true;
    }

    private void isAuthorized(HttpSession session) {
        if (session == null || session.getAttribute("authenticatedUser") == null) {
            throw new AuthorizedException("로그인이 필요합니다.");
        }
    }
}

