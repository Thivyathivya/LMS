package com.LMS.userManagement.controller;

import com.LMS.userManagement.service.CloudStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*")
public class CourseVideoController {

   

    @Autowired
    private CloudStorageService cloudStorageService;

    @PostMapping("/uploadToGcp")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) throws IOException {
        String videoUrl = cloudStorageService.uploadVideo(file);
        return ResponseEntity.ok("Video uploaded successfully. URL: " + videoUrl);
    }



}
