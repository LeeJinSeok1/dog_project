package com.ex.project.controller;

import com.ex.project.dto.AdoptDTO;
import com.ex.project.dto.ApplyDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.service.AdoptService;
import com.ex.project.service.ApplyService;
import com.ex.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplyController {
    private final AdoptService adoptService;
    private final MemberService memberService;

    private final ApplyService applyService;
    @GetMapping("/adoptApplySave/{id}/{memberEmail}")
    public String adoptApplySavePage(@PathVariable Long id,@PathVariable String memberEmail,
                                     Model model){
        System.out.println("memberEmail="+memberEmail);
       AdoptDTO adoptDTO = adoptService.adoptDetail(id);
       MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
       model.addAttribute("adopt",adoptDTO);
       model.addAttribute("member",memberDTO);
       return "/apply/adoptApplySave";
    }

    @PostMapping("/applySave")
    public String applySave(@ModelAttribute ApplyDTO applyDTO){
        applyService.applySave(applyDTO);
        return "/apply/applySuccess";
    }

    @PostMapping("/findApply")
    public @ResponseBody List<ApplyDTO> findApplyList(@RequestParam("memberEmail")String memberEmail){
        List<ApplyDTO> applyDTOList = applyService.findApply(memberEmail);
        return applyDTOList;
    }

    @GetMapping("/applyDetail")
    public String applyDetail(@RequestParam("id") Long id,
                              Model model){
        System.out.println(id);
        ApplyDTO applyDTO = applyService.applyDetail(id);
        MemberDTO memberDTO = memberService.findByMemberEmail(applyDTO.getAdoptWriter());
        System.out.println(memberDTO);
        System.out.println("memberDTO="+ memberDTO);
        model.addAttribute("apply",applyDTO);
        model.addAttribute("member",memberDTO);
        return "/apply/applyDetail";
    }

    @PostMapping("/applyDelete")
    public String applyDelete(@RequestParam("applyId") Long applyId){
        applyService.applyDelete(applyId);
        return "/apply/applyNo";
    }













}
