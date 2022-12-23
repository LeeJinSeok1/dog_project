package com.ex.project.service;

import com.ex.project.dto.DogDTO;
import com.ex.project.dto.MemberDTO;
import com.ex.project.entity.DogEntity;
import com.ex.project.entity.DogFileEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.DogFileRepository;
import com.ex.project.repository.DogRepository;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogSerivce {
    private final DogRepository dogRepository;
    private final MemberRepository memberRepository;
    private final DogFileRepository dogFileRepository;

    @Transactional
    public Long dogSave(DogDTO dogDTO) throws IOException {
        if(dogDTO.getDogFile().isEmpty()) {
            MemberEntity memberEntity = memberRepository.findByMemberEmail(dogDTO.getDogWriter()).get();
            DogEntity dogEntity = DogEntity.toChangeEntity(dogDTO, memberEntity);
            Long savedId = dogRepository.save(dogEntity).getId();
            return savedId;
        }else{
            MemberEntity memberEntity = memberRepository.findByMemberEmail(dogDTO.getDogWriter()).get();
            MultipartFile dogFile = dogDTO.getDogFile();
            String originalFileName = dogFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis()+"_"+originalFileName;
            String savePath = "D:\\dog_project\\" + storedFileName;
            dogFile.transferTo(new File(savePath));


            DogEntity dogEntity = DogEntity.toChangeFileEntity(dogDTO,memberEntity);
            Long savedId = dogRepository.save(dogEntity).getId();

            DogEntity entity =dogRepository.findById(savedId).get();
            DogFileEntity dogFileEntity=
                    DogFileEntity.toSaveDogFileEntity(entity,originalFileName,storedFileName);

            dogFileRepository.save(dogFileEntity);
            return savedId;
        }
    }


    @Transactional
    public DogDTO findDog(String memberEmail) {
        Optional<DogEntity> optionalDogEntity = dogRepository.findByDogWriter(memberEmail);
        if(optionalDogEntity.isPresent()) {
            DogEntity dogEntity = optionalDogEntity.get();
            DogDTO dogDTO = DogDTO.toChangeDogDTO(dogEntity);
            return dogDTO;
        }else{
            return null;
        }
    }
}
