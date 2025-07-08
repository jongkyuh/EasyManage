package com.hjk.EasyManage.controller.finance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("finance/list")

public class FinanceListController {


    @GetMapping("")
    public String goHome(Model model){
        return "finance/financeList";
    }
}
