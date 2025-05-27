package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String start(Model model){
        model.addAttribute("loginForm", new LoginUserDto());
        return "login/login";
//        return "jun"
    }

    @GetMapping("/main")
    public String goMain(@SessionAttribute(name = "loginUser", required = false) String loginUser, Model model){
        // model.addAttribute("session",loginUser);
        if(loginUser == null){
            System.out.println("메인으로 리 다이렉트 ");
            return "redirect:/";
        }
        System.out.println(loginUser);
        return "main";

    }
}
