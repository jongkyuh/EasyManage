package com.hjk.EasyManage.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // 기존 세션 없으면 null

        String loginURI = request.getContextPath() + "/login";
        String signupURI = request.getContextPath() + "/user";

        boolean loggedIn = (session != null && session.getAttribute("loginId") != null);
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean signupRequest = request.getRequestURI().startsWith("/user");

        if (loggedIn || loginRequest || signupRequest) {
            // 로그인 되어있거나 로그인 페이지 요청이면 정상 진행
            filterChain.doFilter(request, response);
        } else {
            // 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
            response.sendRedirect(loginURI);
        }
    }
}

