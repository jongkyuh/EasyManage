package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.user.LoginUserDto;
import com.hjk.EasyManage.entity.Users;
import com.hjk.EasyManage.exception.login.UserNotFoundException;
import com.hjk.EasyManage.exception.login.WrongPasswordException;
import com.hjk.EasyManage.service.login.LoginService;
import com.hjk.EasyManage.service.login.LoginServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            httpSession.setAttribute("loginId", loginUser.getId());
        }catch (UserNotFoundException e){
            model.addAttribute("notFoundErr", e.getMessage());
            System.out.println("없는 아이디");
            return "login/login";
        }catch (WrongPasswordException e){
            model.addAttribute("wrongPasswordErr", e.getMessage());
            System.out.println("비밀번호틀림");
            return "login/login";
        }

        model.addAttribute("session", httpSession.getAttribute("loginUser"));
        return "redirect:main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";

    }

}
