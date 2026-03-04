package org.example.apiexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.apiexam.model.Bread;
import org.example.apiexam.service.BreadService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bread")
@RequiredArgsConstructor
public class BreadController {
    private final BreadService breadService;

    @PreAuthorize("hasAnyRole('CUINER', 'PROPIETARI')")
    @GetMapping
    public List<Bread> getAll() { return breadService.findAll(); }

    @PreAuthorize("hasAnyRole('CUINER', 'PROPIETARI')")
    @GetMapping("/{id}")
    public Bread getById(@PathVariable Long id) { return breadService.findById(id); }
}