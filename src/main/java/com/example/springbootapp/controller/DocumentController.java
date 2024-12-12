package com.example.springbootapp.controller;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.document.DocumentRequestDTO;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.model.request.RequestRequestDTO;
import com.example.springbootapp.model.user.User;
import com.example.springbootapp.service.DocumentService;
import com.example.springbootapp.service.RequestService;
import com.example.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @PostMapping("/create-document")
    public Document createDocument(@RequestBody DocumentRequestDTO request) throws Exception {
        Document document = new Document();
        document.setTitle(request.getTitle());
        document.setDescription(request.getDescription());
        document.setPackageType(request.getPackageType());
        document.setSpecificType(request.getSpecificType());
        document.setUser(userService.findById(request.getUserId()));
        return documentService.createDocument(document);
    }

    @PostMapping("/{documentId}/download")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Integer documentId) throws Exception {
        Document document = documentService.findById(documentId);
        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + document.getTitle() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(document.getContent());
    }
}
