package com.hjk.EasyManage.controller;

import com.hjk.EasyManage.dto.finance.FinanceCategoryRequest;
import com.hjk.EasyManage.dto.finance.FinanceCategoryResponse;
import com.hjk.EasyManage.entity.FinanceType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceController {

    private final WebClient.Builder webClientBuilder;
    @GetMapping("")
    public String goFinanceMain(HttpSession httpSession, Model model){

       return "finance/financeMain";
    }

    @GetMapping("/category/create")
    public String categoryForm(Model model){
        model.addAttribute("categoryForm", new FinanceCategoryRequest());
        model.addAttribute("categoryType", FinanceType.values());
        return "finance/financeCategoryCreate";
    }

    @PostMapping("/category/create")
    public String categorycreate(@ModelAttribute("categoryForm") FinanceCategoryRequest fr, HttpSession httpSession, Model model){
        long userId = (long) httpSession.getAttribute("loginId");
        fr.setUserId(userId);

        FinanceCategoryResponse response = webClientBuilder
                .baseUrl("http://localhost:8083")
                .build()
                .post()
                .uri("/api/finance/create")
                .bodyValue(fr)
                .retrieve()
                .bodyToMono(FinanceCategoryResponse.class)
                .block();


        return "finance/financeMain";

    }



}
