package com.example.springbootapp.service;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.document.DocumentPattern;
import com.example.springbootapp.repository.DocumentPatternRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class DocumentPatternService {

    @Autowired
    private DocumentPatternRepository repository;

    public void save(DocumentPattern documentPattern) {
        repository.save(documentPattern);
    }

    public DocumentPattern findById(int id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Document not found!"));
    }

    public void createDocument(DocumentPattern documentPattern) {
        repository.save(documentPattern);
    }

    public List<DocumentPattern> getAllDocumentPatterns() {
        return repository.findAll();
    }
}
