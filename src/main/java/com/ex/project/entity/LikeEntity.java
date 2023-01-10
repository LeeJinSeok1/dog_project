package com.ex.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="like_table")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity productEntity;

    public static LikeEntity toChangeEntity(MemberEntity memberEntity, ProductEntity productEntity) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setProductEntity(productEntity);
        likeEntity.setMemberEntity(memberEntity);
        return likeEntity;
    }
}
