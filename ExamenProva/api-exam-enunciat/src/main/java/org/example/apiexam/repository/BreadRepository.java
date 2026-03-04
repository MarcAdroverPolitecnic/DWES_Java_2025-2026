package org.example.apiexam.repository;

import org.example.apiexam.model.Bread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreadRepository extends JpaRepository<Bread, Long> {
}