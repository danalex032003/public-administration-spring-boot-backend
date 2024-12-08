package com.example.springbootapp.model.document;

import com.example.springbootapp.util.DocumentTypesUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDTO {
    private String title;
    private String description;
    private DocumentTypesUtil.DocumentPackageTypeEnum packageType;
    private String specificType;
    private Integer userId;
}
