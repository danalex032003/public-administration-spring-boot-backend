package com.example.springbootapp.dto;

import com.example.springbootapp.model.document.DocumentPattern;
import com.example.springbootapp.util.DocumentTypesUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class DocumentPatternDTO {

    private Integer id;
    private String title;
    private String description;
    private DocumentTypesUtil.DocumentPackageTypeEnum packageType;
    private byte[] content;

    public DocumentPatternDTO(DocumentPattern documentPattern) {
        this.id = documentPattern.getId();
        this.title = documentPattern.getTitle();
        this.description = documentPattern.getDescription();
        this.packageType = documentPattern.getPackageType();
        this.content = documentPattern.getContent();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
