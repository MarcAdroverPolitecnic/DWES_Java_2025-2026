package org.example.apiexam.repository;

import org.example.apiexam.model.Bocata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BocataRepository extends JpaRepository<Bocata, Long> {
}