package org.example.apiexam.service;

import lombok.RequiredArgsConstructor;
import org.example.apiexam.dto.BocataDto;
import org.example.apiexam.dto.CreateBocataDto;
import org.example.apiexam.error.ResourceNotFoundException;
import org.example.apiexam.model.Bocata;
import org.example.apiexam.model.Bread;
import org.example.apiexam.repository.BocataRepository;
import org.example.apiexam.repository.BreadRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BocataService {

    private final BocataRepository bocataRepository;
    private final BreadRepository breadRepository;
    private final ModelMapper modelMapper;

    public List<BocataDto> findAll() {
        return bocataRepository.findAll().stream()
                .map(bocata -> modelMapper.map(bocata, BocataDto.class))
                .collect(Collectors.toList());
    }

    public BocataDto findById(Long id) {
        Bocata bocata = bocataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bocata no encontrado"));
        return modelMapper.map(bocata, BocataDto.class);
    }

    public BocataDto save(CreateBocataDto dto) {
        Bread bread = breadRepository.findById(dto.getBreadId())
                .orElseThrow(() -> new ResourceNotFoundException("Pan no encontrado"));

        Bocata bocata = modelMapper.map(dto, Bocata.class);
        bocata.setBread(bread);

        Bocata saved = bocataRepository.save(bocata);
        return modelMapper.map(saved, BocataDto.class);
    }
}