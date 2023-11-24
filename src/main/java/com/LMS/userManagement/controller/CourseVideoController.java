package com.LMS.userManagement.controller;

import com.LMS.userManagement.service.CloudStorageService;
import com.LMS.userManagement.service.DropboxService;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lms/api/auth")
public class CourseVideoController {

    @Autowired
    private DropboxService dropboxService;


    @Autowired
    private CloudStorageService cloudStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestPart("videoFile") MultipartFile videoFile) {
        try {
            dropboxService.uploadVideo(videoFile, "/Apps/Krays-LMS");
            return ResponseEntity.ok("Video uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading video: " + e.getMessage());
        }
    }

    @GetMapping("/download")
    public String downloadFile(@RequestHeader String filePath) throws IOException, DbxException {
      //  String  Path=  "/Apps/Krays-LMS";
        return dropboxService.downloadFile(filePath);
    }



    @PostMapping("/uploadToGcp")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        String videoUrl = cloudStorageService.uploadVideo(file);
        return ResponseEntity.ok("Video uploaded successfully. URL: " + videoUrl);
    }



}
