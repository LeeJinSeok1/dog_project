package com.ex.project.repository;

import com.ex.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

//    void memberPointPlus(Long id, int plusPoint);
    @Modifying
    @Query(value = "update MemberEntity m set m.memberPoint = m.memberPoint + :plusPoint where m.id=:id")
    void memberPointPlus(@Param("id") Long id,
                         @Param("plusPoint") int plusPoint);

    @Modifying
    @Query(value = "update MemberEntity m set m.memberPoint = m.memberPoint - :minusPoint where m.memberEmail=:memberEmail")
    void memberPointMinus(@Param("memberEmail") String memberEmail,
                          @Param("minusPoint") int minusPoint);


}
