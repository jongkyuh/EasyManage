package com.hjk.EasyManage.controller.finance;

import com.hjk.EasyManage.dto.finance.list.FinanceListCreateDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("finance/list")

public class FinanceListController {
    /**
     * 가계부리스트 컨트롤러
     */


    private final WebClient.Builder webClientBuilder;
    @GetMapping("")
    public String goHome(Model model){

        model.addAttribute("listForm", new FinanceListCreateDto());
        return "finance/financeList";
    }

    @PostMapping("/create")
    public String createList(@ModelAttribute("listForm")FinanceListCreateDto financeListCreateDto,
                             Model model, HttpSession httpSession){
        long userId = (long) httpSession.getAttribute("loginId");
        financeListCreateDto.setUserId(userId);

        webClientBuilder.baseUrl("http://localhost:8083")
                .build()
                .post()
                .uri("/api/finance/list/create")
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();

        return "redirect::/finance/list";

    }


}
