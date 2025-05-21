package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    // 회원가입 페이지 이동
    @GetMapping("")
    public String createSignUp(Model model){
        model.addAttribute("signupForm", new SignUpUserDto());
        return "user/signUp";
    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignUpUserDto signUpUserDto, Model model){
        userService.save(signUpUserDto);
        return "main";
    }
}
