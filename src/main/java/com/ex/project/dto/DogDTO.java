package com.ex.project.dto;

import com.ex.project.entity.DogEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DogDTO {
    private Long id;
    private String dogWriter;
    private String dogSpecies;
    private String dogName;
    private String dogAge;
    private String dogWeight;
    //dogFile
    private MultipartFile dogFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;


    public static DogDTO toChangeDogDTO(DogEntity dogEntity) {
        DogDTO dogDTO = new DogDTO();
        dogDTO.setId(dogEntity.getId());
        dogDTO.setDogName(dogEntity.getDogName());
        dogDTO.setDogSpecies(dogEntity.getDogSpecies());
        dogDTO.setDogAge(dogEntity.getDogAge());
        dogDTO.setDogWriter(dogEntity.getDogWriter());
        dogDTO.setDogWeight(dogEntity.getDogWeight());

        if(dogEntity.getFileAttached() ==1){
            dogDTO.setFileAttached(dogDTO.getFileAttached());

            dogDTO.setOriginalFileName(dogEntity.getDogFileEntityList().get(0).getOriginalFileName());
            dogDTO.setStoredFileName(dogEntity.getDogFileEntityList().get(0).getStoredFileName());
        }else{
            dogDTO.setFileAttached(dogDTO.getFileAttached());
        }

        return dogDTO;
    }
}
