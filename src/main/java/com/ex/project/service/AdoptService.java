package com.ex.project.service;

import com.ex.project.dto.AdoptDTO;
import com.ex.project.entity.AdoptEntity;
import com.ex.project.entity.MemberEntity;
import com.ex.project.repository.AdoptRepository;
import com.ex.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptService {
    private final AdoptRepository adoptRepository;
    private final MemberRepository memberRepository;
    public void adoptSave(AdoptDTO adoptDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(adoptDTO.getAdoptWriter()).get();
        AdoptEntity adoptEntity =AdoptEntity.toChangeEntity(adoptDTO,memberEntity);
        adoptRepository.save(adoptEntity);
    }

    public List<AdoptDTO> findAll() {
        List<AdoptEntity> adoptEntityList = adoptRepository.findAll();
        List<AdoptDTO> adoptDTOList = new ArrayList<>();
        for(AdoptEntity adoptEntity: adoptEntityList){
            AdoptDTO adoptDTO = AdoptDTO.toChangeDTO(adoptEntity);
            adoptDTOList.add(adoptDTO);
        }
        return adoptDTOList;
    }

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
}
