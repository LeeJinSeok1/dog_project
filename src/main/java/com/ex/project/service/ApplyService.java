package com.ex.project.service;

import com.ex.project.dto.ApplyDTO;
import com.ex.project.entity.*;
import com.ex.project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final AdoptRepository adoptRepository;
    private final ApplyFileRepository applyFileRepository;
    @Transactional
    public void applySave(ApplyDTO applyDTO) throws IOException {
        if(applyDTO.getApplyFile().size() == 0) {
            AdoptEntity adoptEntity = adoptRepository.findById(applyDTO.getAdoptApplyId()).get();
            MemberEntity memberEntity = memberRepository.findByMemberEmail(applyDTO.getApplyEmail()).get();
            ApplyEntity applyEntity = ApplyEntity.toChangeEntity(applyDTO, adoptEntity, memberEntity);
            applyRepository.save(applyEntity);
        }else{
            AdoptEntity adoptEntity = adoptRepository.findById(applyDTO.getAdoptApplyId()).get();
            MemberEntity memberEntity = memberRepository.findByMemberEmail(applyDTO.getApplyEmail()).get();
            ApplyEntity applyEntity = ApplyEntity.toChangeFileEntity(applyDTO,adoptEntity,memberEntity);
            Long savedId = applyRepository.save(applyEntity).getId();
            ApplyEntity entity = applyRepository.findById(savedId).get();

            for(MultipartFile applyFile : applyDTO.getApplyFile()){
                String originalFileName = applyFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis()+"_"+originalFileName;
                String savePath = "D:\\dog_project\\" + storedFileName;
                applyFile.transferTo(new File(savePath));


                ApplyFileEntity applyFileEntity =
                        ApplyFileEntity.toChangeFileEntity(entity,originalFileName,storedFileName);
                applyFileRepository.save(applyFileEntity);
            }

        }

    }



    @Transactional
    public List<ApplyDTO> findApply(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail).get();
        List<ApplyEntity> applyEntityList = applyRepository.findAllByAdoptWriter(memberEntity.getMemberEmail());
        List<ApplyDTO> applyDTOList = new ArrayList<>();
        for (ApplyEntity applyEntity : applyEntityList){
            ApplyDTO applyDTO = ApplyDTO.toChangeDTO(applyEntity);
            applyDTOList.add(applyDTO);
        }
        if(applyDTOList.isEmpty()){
            return null;
        }else{
            return applyDTOList;
        }

    }
   @Transactional
    public ApplyDTO applyDetail(Long id) {
        ApplyEntity applyEntity = applyRepository.findById(id).get();
        ApplyDTO applyDTO = ApplyDTO.toChangeDTO(applyEntity);
        return applyDTO;
    }



    public void applyDelete(Long applyId) {
        applyRepository.deleteById(applyId);
    }


}



















