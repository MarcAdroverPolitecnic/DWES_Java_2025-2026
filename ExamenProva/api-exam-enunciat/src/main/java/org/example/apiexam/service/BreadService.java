package org.example.apiexam.service;

import lombok.RequiredArgsConstructor;
import org.example.apiexam.error.ResourceNotFoundException;
import org.example.apiexam.model.Bread;
import org.example.apiexam.repository.BreadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreadService {

    private final BreadRepository breadRepository;

    public List<Bread> findAll() {
        return breadRepository.findAll();
    }

    public Bread findById(Long id) {
        return breadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de pan con ID " + id + " no existe."));
    }
}