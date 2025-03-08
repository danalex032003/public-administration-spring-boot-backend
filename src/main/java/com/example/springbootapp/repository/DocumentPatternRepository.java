package com.example.springbootapp.repository;

import com.example.springbootapp.model.document.DocumentPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentPatternRepository extends JpaRepository<DocumentPattern, Integer> {
}
