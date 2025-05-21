package com.hjk.EasyManage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String start(Model model){
        model.addAttribute("helloMessage", "안녕하세요");
        return "main";
    }
}
