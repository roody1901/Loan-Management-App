package com.example.loan_doc.document_manager.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cloud")
public class S3Controller {

    @PostMapping(value = "create")
    public String createBucket(@RequestParam String bucketName){

        return "bucket created successfully";
    }
}
