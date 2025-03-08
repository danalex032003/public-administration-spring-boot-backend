package com.example.springbootapp.repository;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.document.DocumentPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @Modifying
    @Query("SELECT r FROM Document r WHERE r.user.id = :userId")
    List<Document> findAllWhereUserId(@Param("userId") Integer userId);
}
