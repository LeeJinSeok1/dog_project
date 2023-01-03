package com.ex.project.dto;

import com.ex.project.entity.ApplyEntity;
import com.ex.project.entity.ApplyFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    private List<MultipartFile> applyFile;
    private int fileAttached;
    private List<String> originalFileName;
    private List<String> storedFileName;

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

            List<String> originalFileList = new ArrayList<>();
            List<String> storedFileList = new ArrayList<>();
            for (ApplyFileEntity applyFileEntity : applyEntity.getApplyFileEntityList()){
                originalFileList.add(applyFileEntity.getOriginalFileName());
                storedFileList.add(applyFileEntity.getStoredFileName());
            }
            applyDTO.setOriginalFileName(originalFileList);
            applyDTO.setStoredFileName(storedFileList);
        }else{
            applyEntity.setFileAttached(applyEntity.getFileAttached());
        }
        return applyDTO;
    }
}
