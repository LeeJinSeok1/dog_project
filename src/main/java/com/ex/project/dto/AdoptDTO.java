package com.ex.project.dto;

import com.ex.project.entity.AdoptEntity;
import lombok.*;

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
        return adoptDTO;
    }
}
