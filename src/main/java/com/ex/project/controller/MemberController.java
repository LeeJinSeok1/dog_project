package com.ex.project.controller;

import com.ex.project.dto.*;
import com.ex.project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final DogSerivce dogSerivce;
    private final ApplyService applyService;

    private  final AgreeService agreeService;

    private final ProductService productService;

    private final NoService noService;

    private final SuccessService successService;
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
    public String memberSave(@ModelAttribute MemberDTO memberDTO) {
        Long savedId = memberService.memberSave(memberDTO);

        return "/member/memberSaveSuccess";
    }
    //로그인페이지
    @GetMapping("/memberLogin")
    public String memberLoginPage(){
        return "/member/memberLogin";
    }
    //로그인시도 체크
    @PostMapping("/memberLoginCk")
    public @ResponseBody String memberLoginCk(@ModelAttribute MemberDTO memberDTO) {
       MemberDTO result = memberService.memberLoginCk(memberDTO);
        System.out.println(result);
       if(result !=null){
           return "ok";
       }else{
           return "no";
       }
    }
    //로그인처리
    @PostMapping("/memberLogin")
    public String memberLogin(@ModelAttribute MemberDTO memberDTO,
                              HttpSession session,
                              Model model){
        MemberDTO result = memberService.memberLoginCk(memberDTO);
        session.setAttribute("loginEmail",result.getMemberEmail());

        List<ApplyDTO> applyDTOList = applyService.findApply(result.getMemberEmail());
        model.addAttribute("applyList",applyDTOList);

        List<AgreeDTO> agreeDTOList= agreeService.findAgree(result.getMemberEmail());
        model.addAttribute("agreeList",agreeDTOList);

        List<NoDTO> noDTOList = noService.findNo(result.getMemberEmail());
        model.addAttribute("noList",noDTOList);
        //추천리스트
        List<ProductDTO> productDogList = productService.findSpeciesList(result.getMemberEmail());
            model.addAttribute("speciesList",productDogList);

        List<ProductDTO> productDTOList = productService.findByHits();
        model.addAttribute("productHitsList",productDTOList);

        List<SuccessDTO> successDTOList = successService.findList();
        model.addAttribute("successList",successDTOList);

        List<ProductDTO> productLikeList = productService.findByLike(memberDTO.getMemberEmail());
        model.addAttribute("productLikeList",productLikeList);

        System.out.println(productLikeList);

        return "home";
    }
    //로그아웃
    @GetMapping("/memberLogout")
    public String memberLogout(HttpSession session){
        session.invalidate();
        return "home";
    }
    //회원 상세조회
    @GetMapping("/memberDetail/{memberEmail}")
    public String memberDetail(@PathVariable String memberEmail,
                               Model model) {
        MemberDTO result = memberService.findByMemberEmail(memberEmail);
        DogDTO dogDTO = dogSerivce.findDog(memberEmail);
        model.addAttribute("member",result);
        model.addAttribute("dog",dogDTO);
        List<ApplyDTO> applyDTOList = applyService.findApply(memberEmail);
        model.addAttribute("applyList",applyDTOList);
        System.out.println(applyDTOList);
        List<AgreeDTO> agreeDTOList= agreeService.findAgree(memberEmail);
        model.addAttribute("agreeList",agreeDTOList);
        List<NoDTO> noDTOList = noService.findNo(memberEmail);
        model.addAttribute("noList",noDTOList);
        return "/member/memberDetail";
    }
    //회원수정
    @GetMapping("/memberUpdate/{memberEmail}")
    public String memberUpdatePage(@PathVariable String memberEmail,
                               Model model) {
        MemberDTO result = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member",result);
        return "/member/memberUpdate";
    }
    //수정처리
    @PostMapping("/memberUpdate")
    public String memberUpdate(@ModelAttribute MemberDTO memberDTO) {
        memberService.memberUpdate(memberDTO);
        return "/member/memberUpdateSuccess";
    }

    @GetMapping("/memberMain/{memberEmail}")
    public String memberMain(@PathVariable String memberEmail,Model model){
        List<ApplyDTO> applyDTOList = applyService.findApply(memberEmail);
        model.addAttribute("applyList",applyDTOList);
        List<AgreeDTO> agreeDTOList= agreeService.findAgree(memberEmail);
        model.addAttribute("agreeList",agreeDTOList);
        List<NoDTO> noDTOList = noService.findNo(memberEmail);
        model.addAttribute("noList",noDTOList);
        //추천리스트
        List<ProductDTO> productDogList = productService.findSpeciesList(memberEmail);
        model.addAttribute("speciesList",productDogList);
        List<ProductDTO> productDTOList = productService.findByHits();
        model.addAttribute("productHitsList",productDTOList);
        List<SuccessDTO> successDTOList = successService.findList();
        model.addAttribute("successList",successDTOList);
        List<ProductDTO> productLikeList = productService.findByLike(memberEmail);
        model.addAttribute("productLikeList",productLikeList);
        System.out.println(productLikeList);
        return "home";
    }


}
