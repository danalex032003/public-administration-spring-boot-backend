package com.example.springbootapp.service;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.repository.DocumentRepository;
import com.example.springbootapp.util.PdfUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(Document document) {

        return documentRepository.save(document);
    }

    public Document findById(Integer id) {
        return documentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Document not found!"));
    }

    public List<Document> getAllDocuments(Integer userId) {
        return documentRepository.findAll();
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
