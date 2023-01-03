package com.ex.project.dto;

import com.ex.project.entity.AdoptEntity;
import com.ex.project.entity.AdoptFileEntity;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<MultipartFile> adoptFile;
    private int fileAttached;
    private List<String> originalFileName;
    private List<String> storedFileName;

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
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();

            for (AdoptFileEntity adoptFileEntity : adoptEntity.getAdoptFileEntityList()){

                originalFileNameList.add(adoptFileEntity.getOriginalFileName());
                storedFileNameList.add(adoptFileEntity.getStoredFileName());
            }
            adoptDTO.setOriginalFileName(originalFileNameList);
            adoptDTO.setStoredFileName(storedFileNameList);

        }else{
            adoptDTO.setFileAttached(adoptDTO.getFileAttached());
        }
        return adoptDTO;
    }


}
