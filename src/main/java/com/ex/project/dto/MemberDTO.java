package com.ex.project.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private LocalDateTime memberSaveTime;
    private String memberEmail;
    private String memberPass;
    private String memberName;
    private String memberGender;
    private String memberArea;
    private int memberAge;
    private String memberPhone;
    private String memberPurpose;
}
