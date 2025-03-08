package com.example.springbootapp.controller;

import com.example.springbootapp.dto.RequestDTO;
import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.dto.DocumentDTO;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.model.user.User;
import com.example.springbootapp.repository.UserRepository;
import com.example.springbootapp.service.DocumentService;
import com.example.springbootapp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private RequestService requestService;

    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> download(@PathVariable String documentId) {
        Document document = documentService.findById(Integer.parseInt(documentId));
        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + document.getTitle() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(document.getContent());
    }

    @GetMapping("/get/{documentId}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable String documentId) {
        Document document = documentService.findById(Integer.parseInt(documentId));
        return ResponseEntity.ok(new DocumentDTO(document));
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(@PathVariable String userId) {
        List<Document> documents = documentService.getAllDocuments(Integer.parseInt(userId));
        return ResponseEntity.ok(documents.stream().map(DocumentDTO::new).toList());
    }

    @GetMapping("/get-all")
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @PostMapping("/create")
    public Document createDocument(@RequestBody DocumentDTO documentDTO) {
        Document document = new Document();
        document.setTitle(documentDTO.getTitle());
        document.setDescription(documentDTO.getDescription());
        document.setPackageType(documentDTO.getPackageType());
        document.setCreatedAt(documentDTO.getCreatedAt());
        document.setUpdatedAt(documentDTO.getUpdatedAt());
        document.setContent(documentDTO.getContent());
        User user = userRepository.findById(documentDTO.getUserId()).orElseThrow();
        document.setUser(user);
        System.out.println("document");
        documentService.createDocument(document);
        requestService.create(user, document);
        return document;
        //requestService.create(user, document);
    }
}
