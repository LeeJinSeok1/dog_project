package com.ex.project.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDTO {
    private Long id;
    private String applyEmail;
    private String applyGender;
    private String applyAge;
    private Long adoptApplyId;
    private String applyHaveDog;
    private String applyTitle;
    private String applyContents;
}
