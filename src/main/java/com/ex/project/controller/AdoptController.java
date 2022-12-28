package com.ex.project.controller;

import com.ex.project.dto.AdoptDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.service.AdoptService;
import com.ex.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdoptController {
    private final MemberService memberService;

    private final AdoptService adoptService;

    @GetMapping("/adoptSave/{memberEmail}")
    public String adoptSavePage(@PathVariable String memberEmail,
                                Model model) {
        System.out.println(memberEmail);
        MemberDTO memberDTO =memberService.findByMemberEmail(memberEmail);
        System.out.println(memberDTO);
        model.addAttribute("member",memberDTO);
        return "/adopt/adoptSave";
    }

    @PostMapping("/adoptSave")
    public String adoptSave(@ModelAttribute AdoptDTO adoptDTO,
                            Model model) throws IOException {
        System.out.println(adoptDTO);
        adoptService.adoptSave(adoptDTO);
        List<AdoptDTO> adoptDTOList = adoptService.findAll();
        model.addAttribute("adoptList",adoptDTOList);
        return "/adopt/adoptList";
    }

    @GetMapping("/adoptList")
    public String adoptList(Model model){
        List<AdoptDTO> adoptDTOList = adoptService.findAll();
        model.addAttribute("adoptList",adoptDTOList);
        return "/adopt/adoptList";
    }

    @PostMapping("/adoptSearch")
    public String adoptSearch(@RequestParam("type") String type,@RequestParam("q") String q,
                              Model model){
        List<AdoptDTO> searchList = adoptService.adoptSearch(type,q);
        model.addAttribute("adoptList",searchList);
        return "/adopt/adoptList";
    }

    @GetMapping("/adopt")
    public String adopt(@PageableDefault(page=1) Pageable pageable, Model model){
        System.out.println(pageable.getPageNumber());
        Page<AdoptDTO> adoptDTOList = adoptService.paging(pageable);
        model.addAttribute("adoptList",adoptDTOList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < adoptDTOList.getTotalPages()) ? startPage + blockLimit - 1 : adoptDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        System.out.println(pageable.getPageNumber());
        return "/adopt/adoptPaging";
    }



}
