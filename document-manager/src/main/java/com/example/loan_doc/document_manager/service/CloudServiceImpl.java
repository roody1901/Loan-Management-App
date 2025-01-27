package com.example.loan_doc.document_manager.service;

import com.amazonaws.services.chime.AmazonChime;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.loan_doc.document_manager.config.AmazonCloudConfig;
import com.example.loan_doc.document_manager.entity.LoanDocument;
import com.example.loan_doc.document_manager.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CloudServiceImpl implements CloudService{

    @Autowired
    AmazonS3 amazonBucket;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public String createBucket(String bucketName) {
        if(!amazonBucket.doesBucketExistV2(bucketName)){
           amazonBucket.createBucket(bucketName);
        }
        return bucketName;
    }
    @Override
    public String deleteBucket(String bucketName) {
        if(amazonBucket.doesBucketExistV2(bucketName)){
            amazonBucket.deleteBucket(bucketName);
        }
        return "bucket named as" + bucketName +  "is deleted successfully";
    }

    @Override
    public List<?> allBuckets() {
        return amazonBucket.listBuckets();
    }


    @Override
    public String uploadToBucket(MultipartFile file, String bucketName)  {
        if(amazonBucket.doesBucketExistV2(bucketName)){
            String fileName = file.getOriginalFilename();

            //to store file details in database
            LoanDocument loanDoc = new LoanDocument();
            loanDoc.setFileName(fileName);
            loanDoc.setFileSize(file.getSize());

            //now bucket need a File type of data , and cause of http protocal we used MultiPart to upload file
            File convertedToFile = ConvertToFile(file);

            amazonBucket.putObject(new PutObjectRequest(bucketName,fileName,convertedToFile));

            documentRepository.save(loanDoc);
        }
        return "file uploaded";
    }

    public File ConvertToFile(MultipartFile file)  {

        File convertedFile = new File(file.getOriginalFilename());
        try{
            convertedFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();
        }catch (IOException ex){
            throw  new RuntimeException( ex);
        }
        return convertedFile;
    }
}
