package com.example.loan_doc.document_manager.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
@Configuration
public class AmazonCloudConfig {
    @Bean
    public AmazonS3 configAmazon(){
        String sqsEndpoint = "http://localhost:4572/";
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4572/","us-east-1"))
.withCredentials(new DefaultAWSCredentialsProviderChain())
.withPathStyleAccessEnabled(true)
.build();
return s3;
}
}