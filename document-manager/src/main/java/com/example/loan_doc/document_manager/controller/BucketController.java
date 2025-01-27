package com.example.loan_doc.document_manager.controller;


import com.example.loan_doc.document_manager.entity.LoanDocument;
import com.example.loan_doc.document_manager.service.CloudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/drive")
public class BucketController {

    @Autowired
    private CloudServiceImpl cloudService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file, @RequestParam String bucketName){
            cloudService.uploadToBucket(file,bucketName);
        return new ResponseEntity<>("file uploaded", HttpStatus.OK);
    }
}
