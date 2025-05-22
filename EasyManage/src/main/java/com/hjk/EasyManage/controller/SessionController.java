package com.hjk.EasyManage.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SessionController {

    @GetMapping("/session/check")
    public String check(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            System.out.println("세션 없음 또는 만료됨!");
            return "redirect:/login";  // 로그인 페이지로 이동
        }
        System.out.println("세션 유지중, 로그인 유저: " + session.getAttribute("loginUser"));
        return "redirect:/user";  // 세션 유지 시 이동할 페이지
    }

}
