package com.ex.project.dto;

import com.ex.project.entity.ApplyEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String applyHaveDog;
    private String applyTitle;
    private String applyContents;
    private String adoptWriter;
    private Long adoptApplyId;

    private MultipartFile applyFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public static ApplyDTO toChangeDTO(ApplyEntity applyEntity) {
        ApplyDTO applyDTO = new ApplyDTO();
        applyDTO.setId(applyEntity.getId());
        applyDTO.setApplyAge(applyEntity.getApplyAge());
        applyDTO.setApplyEmail(applyEntity.getApplyEmail());
        applyDTO.setApplyGender(applyEntity.getApplyGender());
        applyDTO.setApplyTitle(applyEntity.getApplyTitle());
        applyDTO.setApplyContents(applyEntity.getApplyContents());
        applyDTO.setAdoptApplyId(applyEntity.getAdoptApplyId());
        applyDTO.setApplyHaveDog(applyEntity.getApplyHaveDog());
        applyDTO.setAdoptWriter(applyEntity.getAdoptWriter());


        if(applyEntity.getFileAttached() == 1){

            applyEntity.setFileAttached(applyEntity.getFileAttached());
            applyDTO.setOriginalFileName(applyEntity.getApplyFileEntityList().get(0).getOriginalFileName());
            applyDTO.setStoredFileName(applyEntity.getApplyFileEntityList().get(0).getStoredFileName());
        }else{
            applyEntity.setFileAttached(applyEntity.getFileAttached());
        }
        return applyDTO;
    }
}
