package com.ex.project.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {

    private Long id;

    private String orderEmail;

    private int orderPrice;

    private String orderArea;

    private String orderPhone;

    private LocalDateTime orderTime;
}
