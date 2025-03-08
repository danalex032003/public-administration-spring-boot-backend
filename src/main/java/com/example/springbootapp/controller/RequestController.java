package com.example.springbootapp.controller;

import com.example.springbootapp.dto.RequestDTO;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.service.RequestService;
import com.example.springbootapp.util.RequestStatusEnum;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/update-status/{requestId}")
    public void updateStatus(@PathVariable Integer requestId, @RequestParam("status") RequestStatusEnum status) {
        requestService.updateStatus(requestId, status);
    }

    @GetMapping("/get/{documentId}")
    public ResponseEntity<RequestDTO> getRequest(@PathVariable String documentId) {
        Request request = requestService.findByDocumentId(Integer.parseInt(documentId));
        return ResponseEntity.ok(new RequestDTO(request));
    }

    @GetMapping("/get-for-user/{userId}")
    public ResponseEntity<List<RequestDTO>> getRequestForUser(@PathVariable String userId) {
        List<Request> requests = requestService.findAllByUserId(Integer.parseInt(userId));
        return ResponseEntity.ok(requests.stream().map(RequestDTO::new).toList());
    }

    @GetMapping("/getall")
    public List<Request> getAllRequest() {
        List<Request> requests = requestService.findAll();
        return requests;
        //return ResponseEntity.ok(requests.stream().map(RequestDTO::new).toList());
    }


    @GetMapping("/get-all-processing")
    public ResponseEntity<List<RequestDTO>> getAllProcessingRequests() {
        List<Request> requests = requestService.findAllProcessingRequests();

        return ResponseEntity.ok(requests.stream().map(RequestDTO::new).toList());
    }
}