package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String start(Model model){
        model.addAttribute("loginForm", new LoginUserDto());
        return "login/login";
    }
}
