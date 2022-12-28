package com.ex.project.service;

import com.ex.project.dto.AdoptDTO;
import com.ex.project.entity.AdoptEntity;
import com.ex.project.entity.AdoptFileEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.AdoptFIleRepository;
import com.ex.project.repository.AdoptRepository;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdoptService {
    private final AdoptRepository adoptRepository;
    private final MemberRepository memberRepository;
    private final AdoptFIleRepository adoptFIleRepository;
    public void adoptSave(AdoptDTO adoptDTO) throws IOException {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(adoptDTO.getAdoptWriter()).get();
        if(adoptDTO.getAdoptFile().isEmpty()) {
            AdoptEntity adoptEntity = AdoptEntity.toChangeEntity(adoptDTO, memberEntity);
            adoptRepository.save(adoptEntity);
        }else{
            MultipartFile adoptFile = adoptDTO.getAdoptFile();
            String originalFileName =adoptFile.getOriginalFilename();
            String storedFileName =System.currentTimeMillis()+"_"+originalFileName;
            String savePath = "D:\\dog_project\\" +storedFileName;
            adoptFile.transferTo(new File(savePath));

            AdoptEntity adoptEntity = AdoptEntity.toChangeFileEntity(adoptDTO,memberEntity);
            Long savedId = adoptRepository.save(adoptEntity).getId();

            AdoptEntity adopt = adoptRepository.findById(savedId).get();
            AdoptFileEntity adoptFileEntity =
                    AdoptFileEntity.toSaveAdoptFileEntity(adopt,originalFileName,storedFileName);
            adoptFIleRepository.save(adoptFileEntity);

        }
    }
    @Transactional
    public List<AdoptDTO> findAll() {
        List<AdoptEntity> adoptEntityList = adoptRepository.findAll();
        List<AdoptDTO> adoptDTOList = new ArrayList<>();
        for(AdoptEntity adoptEntity: adoptEntityList){
            AdoptDTO adoptDTO = AdoptDTO.toChangeDTO(adoptEntity);
            adoptDTOList.add(adoptDTO);
        }
        return adoptDTOList;
    }
    @Transactional
    public List<AdoptDTO> adoptSearch(String type, String q) {
        List<AdoptDTO> adoptDTOList = new ArrayList<>();
        List<AdoptEntity> adoptEntityList = null;
        if(type.equals("adoptSpecies")){
            adoptEntityList = adoptRepository.findByAdoptSpeciesContainingOrderByIdDesc(q);
        }else if(type.equals("adoptArea")){
            adoptEntityList = adoptRepository.findByAdoptAreaContainingOrderByIdDesc(q);
        }

        for (AdoptEntity adoptEntity : adoptEntityList) {
            adoptDTOList.add(AdoptDTO.toChangeDTO(adoptEntity));
        }
        return adoptDTOList;
    }


    public Page<AdoptDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1 ;
        final int pageLimit = 3;
        Page<AdoptEntity> adoptEntities = adoptRepository.findAll(PageRequest.of
                (page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        Page<AdoptDTO> adoptList = adoptEntities.map(
                adopt -> new AdoptDTO(adopt.getId(),
                        adopt.getAdoptWriter(),
                        adopt.getAdoptTitle(),
                        adopt.getAdoptArea(),
                        adopt.getAdoptSpecies(),
                        adopt.getAdoptSaveTime()
                )
        );
        return adoptList;
    }


    public Page<AdoptDTO> searchPaging(String type, String q, Pageable pageable) {
        int page = pageable.getPageNumber() -1 ;
        final int pageLimit = 3;
        if(type.equals("adoptSpecies")){
            Page<AdoptEntity> adoptEntities = adoptRepository.findByAdoptSpeciesContainingOrderByIdDesc(q,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
            Page<AdoptDTO> adoptList = adoptEntities.map(
                    adopt -> new AdoptDTO(adopt.getId(),
                            adopt.getAdoptWriter(),
                            adopt.getAdoptTitle(),
                            adopt.getAdoptArea(),
                            adopt.getAdoptSpecies(),
                            adopt.getAdoptSaveTime()
                    )
            );
            return adoptList;
        }else if(type.equals("adoptArea")){
            Page<AdoptEntity> adoptEntities = adoptRepository.findByAdoptAreaContainingOrderByIdDesc(q,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
            Page<AdoptDTO> adoptList = adoptEntities.map(
                    adopt -> new AdoptDTO(adopt.getId(),
                            adopt.getAdoptWriter(),
                            adopt.getAdoptTitle(),
                            adopt.getAdoptArea(),
                            adopt.getAdoptSpecies(),
                            adopt.getAdoptSaveTime()
                    )
            );
            return adoptList;
        }
        return null;
    }
    @Transactional
    public AdoptDTO adoptDetail(Long id) {
        Optional<AdoptEntity> optionalAdoptEntity = adoptRepository.findById(id);
        AdoptEntity adoptEntity = optionalAdoptEntity.get();
        AdoptDTO adoptDTO = AdoptDTO.toChangeDTO(adoptEntity);
        return adoptDTO;
    }
}
