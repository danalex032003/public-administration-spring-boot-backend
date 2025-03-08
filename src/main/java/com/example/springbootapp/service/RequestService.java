package com.example.springbootapp.service;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.model.user.User;
import com.example.springbootapp.repository.RequestRepository;
import com.example.springbootapp.util.RequestStatusEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void updateStatus(Integer requestId, RequestStatusEnum status) {
        Date processedAt = status == RequestStatusEnum.APPROVED ? new Date() : null;
        Integer rowsProcessed = requestRepository.updateStatus(requestId, status, processedAt);
        if (rowsProcessed == 0) {
            throw new EntityNotFoundException("Request not found with ID:" + requestId);
        }
    }

    public Request create(User user, Document document) {
        Request request = new Request();
        request.setUser(user);
        request.setStatus(RequestStatusEnum.PROCESSING);
        request.setDocument(document);
        return requestRepository.save(request);
    }

    public Request findById(Integer requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public List<Request> findAllProcessingRequests() {
        return requestRepository.findAllProcessingRequests();
    }

    public Request findByDocumentId(Integer documentId) {
        return requestRepository.findByDocumentId(documentId).orElseThrow(() -> new EntityNotFoundException("Request not found!"));
    }

    public List<Request> findAllByUserId(Integer i) {
        return requestRepository.findAllByUserId(i);
    }
}
