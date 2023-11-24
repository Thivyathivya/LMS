package com.LMS.userManagement.config;

import com.LMS.userManagement.util.GcpUtil;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudStorageConfig {


   // @Value("${gcp.projectId}")
    private String projectId=GcpUtil.PROJECT_ID;

   // @Value("${gcp.storage.bucketName}")
    private String bucketName=GcpUtil.BUCKET_NAME;

    @Bean
    public Storage storage() {
        return StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    }

    public String getBucketName() {
        return bucketName;
    }
}
