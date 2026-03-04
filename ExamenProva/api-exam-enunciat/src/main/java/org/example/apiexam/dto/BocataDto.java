package org.example.apiexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BocataDto {
    private Long id;
    private String name;
    private double price;
    private String breadName;
}