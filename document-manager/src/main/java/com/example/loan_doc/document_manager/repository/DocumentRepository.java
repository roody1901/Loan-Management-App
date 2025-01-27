package com.example.loan_doc.document_manager.repository;

import com.example.loan_doc.document_manager.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<LoanDocument,Long> {
}
