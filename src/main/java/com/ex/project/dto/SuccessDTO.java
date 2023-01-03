package com.ex.project.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SuccessDTO {
    private Long id;
    private Long agreeId;
    private String agreeWriter;
    private String agreeApplyWriter;
    private String agreeContents;
}
