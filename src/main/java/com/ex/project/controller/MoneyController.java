package com.ex.project.controller;

import com.ex.project.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;
    @GetMapping("/moneyCharging")
    public String moneyChargingPage(){
        return "/money/moneyCharging";
    }
    @PostMapping("/moneyCharging")
    public @ResponseBody void moneyCharging(@RequestParam("memberEmail") String memberEmail,
                                            @RequestParam("plusPoint") int plusPoint){
        System.out.println("memberEmail="+memberEmail+"memberPoint="+plusPoint);
        moneyService.moneyCharging(memberEmail,plusPoint);

    }
    @GetMapping("/moneySuccess")
    public String moneySuccess(){
        return "/money/moneySuccess";
    }

}
