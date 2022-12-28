package com.ex.project.dto;

import com.ex.project.entity.AdoptEntity;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdoptDTO {
    private Long id;
    private String adoptWriter;
    private String adoptName;
    private String adoptSpecies;
    private String adoptAge;
    private String adoptTitle;
    private String adoptContents;
    private String adoptArea;
    private LocalDateTime adoptSaveTime;

    private MultipartFile adoptFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public AdoptDTO(Long id, String adoptWriter, String adoptTitle,
                    String adoptArea, String adoptSpecies, LocalDateTime adoptSaveTime) {
        this.id = id;
        this.adoptWriter = adoptWriter;
        this.adoptTitle = adoptTitle;
        this.adoptArea = adoptArea;
        this.adoptSpecies = adoptSpecies;
        this.adoptSaveTime = adoptSaveTime;
    }

    public static AdoptDTO toChangeDTO(AdoptEntity adoptEntity) {
        AdoptDTO adoptDTO = new AdoptDTO();
        adoptDTO.setId(adoptEntity.getId());
        adoptDTO.setAdoptWriter(adoptEntity.getAdoptWriter());
        adoptDTO.setAdoptName(adoptEntity.getAdoptName());
        adoptDTO.setAdoptAge(adoptEntity.getAdoptAge());
        adoptDTO.setAdoptSpecies(adoptEntity.getAdoptSpecies());
        adoptDTO.setAdoptTitle(adoptEntity.getAdoptTitle());
        adoptDTO.setAdoptArea(adoptEntity.getAdoptArea());
        adoptDTO.setAdoptSaveTime(adoptEntity.getAdoptSaveTime());
        adoptDTO.setAdoptContents(adoptEntity.getAdoptContents());

        if(adoptEntity.getFileAttached() ==1){
            adoptDTO.setFileAttached(adoptDTO.getFileAttached());
            adoptDTO.setOriginalFileName(adoptEntity.getAdoptFileEntityList().get(0).getOriginalFileName());
            adoptDTO.setStoredFileName(adoptEntity.getAdoptFileEntityList().get(0).getStoredFileName());
        }else{
            adoptDTO.setFileAttached(adoptDTO.getFileAttached());
        }
        return adoptDTO;
    }


}
