package com.LMS.userManagement.service;

import com.LMS.userManagement.config.CloudStorageConfig;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CloudStorageService {

    @Autowired
    private Storage storage;

    @Autowired
    private CloudStorageConfig cloudStorageConfig;

    public String uploadVideo(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String blobName = "login.html"; // Use a proper path for your videos

        BlobId blobId = BlobId.of(cloudStorageConfig.getBucketName(), blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    storage.create(blobInfo, file.getBytes());

       return "gs://" + cloudStorageConfig.getBucketName() + "/" + blobName;
    }
}
