package com.ex.project.service;

import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MoneyService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    @Transactional
    public void moneyCharging(String memberEmail, int plusPoint) {
        Long id = memberService.findByMemberEmail(memberEmail).getId();
        memberRepository.memberPointPlus(id,plusPoint);
    }
}
