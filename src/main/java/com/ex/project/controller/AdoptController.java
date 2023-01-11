package com.ex.project.controller;

import com.ex.project.dto.AdoptDTO;
import com.ex.project.dto.ApplyDTO;
import com.ex.project.dto.CommentDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.service.AdoptService;
import com.ex.project.service.ApplyService;
import com.ex.project.service.CommentService;
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

    private final ApplyService applyService;
    private final CommentService commentService;
    // 분양 글 저장 페이지
    @GetMapping("/adoptSave/{memberEmail}")
    public String adoptSavePage(@PathVariable String memberEmail,
                                Model model) {
        System.out.println(memberEmail);
        MemberDTO memberDTO =memberService.findByMemberEmail(memberEmail);
        System.out.println(memberDTO);
        model.addAttribute("member",memberDTO);
        return "/adopt/adoptSave";
    }
    // 분양 글 저장
    @PostMapping("/adoptSave")
    public String adoptSave(@ModelAttribute AdoptDTO adoptDTO,
                            Model model) throws IOException {
        System.out.println(adoptDTO);
        adoptService.adoptSave(adoptDTO);
        List<AdoptDTO> adoptDTOList = adoptService.findAll();
        model.addAttribute("adoptList",adoptDTOList);
        return "redirect:adopt";
    }

    // 비회원 리스트
    @GetMapping("/adoptList")
    public String adoptList(Model model){
        List<AdoptDTO> adoptDTOList = adoptService.findAll();
        model.addAttribute("adoptList",adoptDTOList);
        return "/adopt/adoptList";
    }

    //비회원 검색 후 리스트 처리
    @PostMapping("/adoptSearch")
    public String adoptSearch(@RequestParam("type") String type,@RequestParam("q") String q,
                              Model model){
        List<AdoptDTO> searchList = adoptService.adoptSearch(type,q);
        model.addAttribute("adoptList",searchList);

        return "/adopt/adoptList";
    }


    // 페이징 리스트 처리
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
    //검색 후 페이징 처리
    @PostMapping("/adoptSearchPaging")
    public String adoptSearch(@RequestParam("type") String type,@RequestParam("q") String q,
                          Model model,@PageableDefault(page=1) Pageable pageable){

    Page<AdoptDTO> adoptDTOList = adoptService.searchPaging(type,q,pageable);
    model.addAttribute("adoptList",adoptDTOList);
    int blockLimit = 3;
    int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
    int endPage = ((startPage + blockLimit - 1) < adoptDTOList.getTotalPages()) ? startPage + blockLimit - 1 : adoptDTOList.getTotalPages();
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    System.out.println(pageable.getPageNumber());
    return "/adopt/adoptPaging";
    }



    @GetMapping("/adoptDetail/{id}")
    public String adoptDetail(@PathVariable Long id,
                              Model model){
        System.out.println("ID="+id);
       AdoptDTO adoptDTO = adoptService.adoptDetail(id);
       model.addAttribute("adopt",adoptDTO);
        System.out.println("adopt="+adoptDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        if (commentDTOList.size() > 0) {
            model.addAttribute("commentList", commentDTOList);
        } else {
            model.addAttribute("commentList", "empty");
        }
       return "/adopt/adoptDetail";
    }



}
