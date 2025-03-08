package com.example.springbootapp.dto;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.model.user.User;
import com.example.springbootapp.util.RequestStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestDTO {

    private Integer id;

    private RequestStatusEnum status;

    private Date createdAt;

    private Date processedAt;

    private String remarks;

    private Integer userId;

    private Integer processedByUserId;

    private Integer documentId;

    public RequestDTO(Request request) {
        this.id = request.getId();
        this.status = request.getStatus();
        this.createdAt = request.getCreatedAt();
        this.processedAt = request.getProcessedAt();
        this.remarks = request.getRemarks();
        this.userId = request.getUser() != null ? request.getUser().getId() : null;
        this.processedByUserId = request.getProcessedByUser() != null ? request.getProcessedByUser().getId() : null;
        this.documentId = request.getDocument() != null ? request.getDocument().getId() : null;
    }
}
