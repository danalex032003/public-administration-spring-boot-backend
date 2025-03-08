package com.example.springbootapp.controller;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.document.DocumentPattern;
import com.example.springbootapp.dto.DocumentPatternDTO;
import com.example.springbootapp.service.DocumentPatternService;
import com.example.springbootapp.service.DocumentService;
import com.example.springbootapp.util.DocumentTypesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document-pattern")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentPatternController {

    @Autowired
    private DocumentPatternService documentPatternService;

    @Autowired
    private DocumentService documentService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        DocumentPattern documentPattern = documentPatternService.findById(Integer.parseInt(id));
        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + documentPattern.getTitle() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(documentPattern.getContent());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<DocumentPattern> getDocumentPattern(@PathVariable String id) {
        return ResponseEntity.ok(documentPatternService.findById(Integer.parseInt(id)));
    }

    @GetMapping("/gett/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable String id) {

        return ResponseEntity.ok(documentService.findById(Integer.parseInt(id)));
    }

    @GetMapping("/getall")
    public List<DocumentPattern> getAllDocumentPatterns() {
        return documentPatternService.getAllDocumentPatterns();
    }

    @PostMapping("/create")
    public void createDocumentPattern(@RequestBody DocumentPatternDTO documentPatternDTO) {
        System.out.println("TAG: " + documentPatternDTO);
        DocumentPattern documentPattern = new DocumentPattern();
        documentPattern.setTitle(documentPatternDTO.getTitle());
        documentPattern.setDescription(documentPatternDTO.getDescription());
        documentPattern.setPackageType(documentPatternDTO.getPackageType());
        documentPattern.setContent(documentPatternDTO.getContent());
        documentPatternService.createDocument(documentPattern);
    }

    @PostMapping("/test")
    public int test(@RequestBody int x) {
        return x;
    }
}
