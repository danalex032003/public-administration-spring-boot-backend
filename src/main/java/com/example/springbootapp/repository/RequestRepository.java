package com.example.springbootapp.repository;

import com.example.springbootapp.model.document.Document;
import com.example.springbootapp.model.request.Request;
import com.example.springbootapp.util.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Modifying
    @Query("UPDATE Request r SET r.status = :status, r.processedAt = :processedAt WHERE r.id = :id")
    Integer updateStatus(@Param("id") Integer id,
                     @Param("status") RequestStatusEnum status,
                     @Param("processedAt") Date processedAt);

    @Modifying
    @Query("SELECT r from Request r where r.status = 'PROCESSING'")
    List<Request> findAllProcessingRequests();

    List<Request> document(Document document);

    @Query("SELECT r FROM Request r WHERE r.document.id = :documentId")
    Optional<Request> findByDocumentId(@Param("documentId") Integer documentId);

    @Query("SELECT r from Request r where r.user.id = :i")
    List<Request> findAllByUserId(Integer i);
}
