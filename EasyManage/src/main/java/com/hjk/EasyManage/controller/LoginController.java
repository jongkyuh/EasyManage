package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.exception.login.UserNotFoundException;
import com.hjk.EasyManage.exception.login.WrongPasswordException;
import com.hjk.EasyManage.service.login.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginUserDto());
        return "login/login";
    }

    @PostMapping("")
    public String login(@ModelAttribute("loginForm") LoginUserDto loginUserDto, BindingResult bindingResult, HttpSession httpSession, Model model){

        try {
            Users loginUser = loginService.login(loginUserDto);
            httpSession.setAttribute("loginUser", loginUser.getUsername());
        }catch (UserNotFoundException e){
            model.addAttribute("notFoundErr", e.getMessage());
            return "login/login";
        }catch (WrongPasswordException e){
            model.addAttribute("wrongPasswordErr", e.getMessage());
            return "login/login";
        }

        model.addAttribute("session", httpSession.getAttribute("loginUser"));
        return "main";
    }

}
