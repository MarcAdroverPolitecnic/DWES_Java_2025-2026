package org.example.apiexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.apiexam.dto.BocataDto;
import org.example.apiexam.dto.CreateBocataDto;
import org.example.apiexam.service.BocataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bocata")
@RequiredArgsConstructor
public class BocataController {
    private final BocataService bocataService;

    @GetMapping
    public List<BocataDto> getAll() { return bocataService.findAll(); }

    @GetMapping("/{id}")
    public BocataDto getById(@PathVariable Long id) { return bocataService.findById(id); }

    @PreAuthorize("hasRole('PROPIETARI')")
    @PostMapping
    public ResponseEntity<BocataDto> create(@RequestBody CreateBocataDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bocataService.save(dto));
    }
}
