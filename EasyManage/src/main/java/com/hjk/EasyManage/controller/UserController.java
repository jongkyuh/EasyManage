package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import com.hjk.EasyManage.dto.user.SignUpUserDto;
import com.hjk.EasyManage.exception.user.UserAlreadyExistsException;
import com.hjk.EasyManage.service.user.UserService;
import com.hjk.EasyManage.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    // 회원가입 페이지 이동
    @GetMapping("")
    public String createSignUp(HttpSession httpSession, Model model) {

        model.addAttribute("signupForm", new SignUpUserDto());
        return "user/signUp";
    }


    // 회원가입
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signupForm") SignUpUserDto signUpUserDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/signUp";
        }
        try{
            userService.save(signUpUserDto);
        }catch (UserAlreadyExistsException e){
            model.addAttribute("alreadyExistsErr",e.getMessage());
            return "user/signUp";
        }

        model.addAttribute("loginForm",new LoginUserDto());
        return "login/login";

    }


}
