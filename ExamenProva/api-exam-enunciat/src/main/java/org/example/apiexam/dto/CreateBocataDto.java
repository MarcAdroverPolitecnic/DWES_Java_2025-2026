package org.example.apiexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBocataDto {
    private String name;
    private double price;
    private Long breadId;
}