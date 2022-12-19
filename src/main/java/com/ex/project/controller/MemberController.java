package com.ex.project.controller;

import com.ex.project.dto.MemberDTO;
import com.ex.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    //회원가입 페이지로 이동
    @GetMapping("/memberSave")
    public String memberSavePage() {
        return "/member/memberSave";
    }
    //이메일 중복 체크 ajax
    @PostMapping("/memberEmailCk")
    public @ResponseBody String memberEmailCk(@RequestParam("memberEmail") String memberEmail){
        MemberDTO result= memberService.findByMemberEmail(memberEmail);
        System.out.println(result);
        if(result ==null){
            return "yes";
        }else{
            return "no";
        }
    }
    //회원가입처리
    @PostMapping("/memberSave")
    public String memberSave(@ModelAttribute MemberDTO memberDTO,
                             HttpSession session) {
        Long savedId = memberService.memberSave(memberDTO);

        return "/member/memberSaveSuccess";
    }

}
