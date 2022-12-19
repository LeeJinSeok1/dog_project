package com.ex.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/memberSave")
    public String memberSavePage() {
        return "/member/memberSave";
    }
}
