package com.example.loan_doc.document_manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudService {

    String createBucket(String bucketName);
    String deleteBucket(String bucketName);

    List<?> allBuckets();

    String uploadToBucket(MultipartFile file, String bucketName) throws IOException;
}
