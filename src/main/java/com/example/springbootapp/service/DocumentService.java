package com.example.springbootapp.service;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.repository.DocumentRepository;
import com.example.springbootapp.util.PdfUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document createDocument(Document document) throws Exception {
        String content = "Title: " + document.getTitle() + "Description: " + document.getDescription();
        byte[] pdfBytes = PdfUtil.generatePdf(document.getTitle(), content);
        document.setContent(pdfBytes);
        return documentRepository.save(document);
    }

    public Document findById(Integer id) {
        return documentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Document not found!"));
    }
}
