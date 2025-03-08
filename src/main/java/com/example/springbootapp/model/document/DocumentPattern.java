package com.example.springbootapp.model.document;

import com.example.springbootapp.util.DocumentTypesUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.LobTypeMappings;

@Entity
@Table(name = "document_pattern")
@Getter
@Setter
public class DocumentPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "package_type")
    private DocumentTypesUtil.DocumentPackageTypeEnum packageType;

    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] content;

}
