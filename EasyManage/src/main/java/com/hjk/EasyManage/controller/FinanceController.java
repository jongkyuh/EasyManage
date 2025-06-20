package com.hjk.EasyManage.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceController {

    private final WebClient.Builder webClientBuilder;
    @GetMapping("")
    public String goFinance(HttpSession httpSession, Model model){

        long loginId = (long) httpSession.getAttribute("loginId");  // 로그인한 아이디값

        String username = webClientBuilder
                .baseUrl("http://localhost:8083/api/finance")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("userId", loginId).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        model.addAttribute("username",username);
        return "finance/finance";
    }





}
