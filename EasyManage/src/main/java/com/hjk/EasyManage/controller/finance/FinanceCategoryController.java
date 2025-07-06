package com.hjk.EasyManage.controller.finance;

import com.hjk.EasyManage.dto.finance.FinanceCategoryRequest;
import com.hjk.EasyManage.dto.finance.FinanceCategoryResponse;
import com.hjk.EasyManage.entity.FinanceType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceCategoryController {
     static long num = 1;

    private final WebClient.Builder webClientBuilder;
    @GetMapping("")
    public String goFinanceMain(HttpSession httpSession, Model model){

        return "finance/financeMain";
    }

    @GetMapping("/category/create")
    public String categoryForm(HttpSession httpSession, Model model){
        long userId = (long) httpSession.getAttribute("loginId");
        List<FinanceCategoryResponse> getCategoryList = webClientBuilder.baseUrl("http://localhost:8083")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/finance/category/list")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToFlux(FinanceCategoryResponse.class)
                .collectList()
                .block();

        // 로그인한 유저의 카테고리리스트
        model.addAttribute("categoryList", getCategoryList);
        // 카테고리 폼, 카테고리타입
        model.addAttribute("categoryForm", new FinanceCategoryRequest());
        model.addAttribute("categoryType", FinanceType.values());


        return "finance/financeCategory";
    }

    @PostMapping("/category/create")
    public String categorycreate(@ModelAttribute("categoryForm") FinanceCategoryRequest fr, HttpSession httpSession, Model model){
        long userId = (long) httpSession.getAttribute("loginId");
        fr.setUserId(userId);
        fr.setCategoryName(fr.getCategoryName() + num);
        num += 1;
        FinanceCategoryResponse response = webClientBuilder
                .baseUrl("http://localhost:8083")
                .build()
                .post()
                .uri("/api/finance/category/create")
                .bodyValue(fr)
                .retrieve()
                .bodyToMono(FinanceCategoryResponse.class)
                .block();


        return "redirect:/finance/category/create";

    }

    @PostMapping("/category/delete/{categoryId}")
    @ResponseBody
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "categoryId") long categoryId, HttpSession httpSession){
        long userId = (long) httpSession.getAttribute("loginId");


        ResponseEntity<Void> response = webClientBuilder
                .baseUrl("http://localhost:8083")
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/finance/category/delete/{categoryId}")
                        .queryParam("userId", userId)
                        .build(categoryId))
                .retrieve()
                .toBodilessEntity()
                .block();

        return response;

    }



}
