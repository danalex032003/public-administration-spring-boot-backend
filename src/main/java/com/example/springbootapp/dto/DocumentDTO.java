package com.example.springbootapp.dto;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.util.DocumentTypesUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DocumentDTO {
    private Integer id;
    private String title;
    private String description;
    private DocumentTypesUtil.DocumentPackageTypeEnum packageType;
    @JsonProperty("created")
    private Date createdAt;
    @JsonProperty("modified")
    private Date updatedAt;
    private Integer userId;
    private byte[] content;

    public DocumentDTO(Document document) {
        this.id = document.getId();
        this.title = document.getTitle();
        this.description = document.getDescription();
        this.packageType = document.getPackageType();
        this.content = document.getContent();
        this.createdAt = document.getCreatedAt();
        this.updatedAt = document.getUpdatedAt();
        this.userId = document.getUser() != null ? document.getUser().getId() : null;
    }

    public DocumentDTO() {}
}
