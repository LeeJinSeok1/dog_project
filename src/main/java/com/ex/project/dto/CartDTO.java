package com.ex.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDTO {
    private Long id;
    private Long productId;
    private String memberEmail;
    private int price;
    private String productName;
    private int totalPrice;
    private int totalCount;
}
