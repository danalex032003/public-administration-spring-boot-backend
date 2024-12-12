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
}
