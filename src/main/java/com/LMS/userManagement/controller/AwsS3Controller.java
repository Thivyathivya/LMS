package com.LMS.userManagement.controller;

import com.LMS.userManagement.awsS3.AWSS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/lms/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AwsS3Controller {


    @Autowired
    AWSS3Service awss3Service;

    @PostMapping("/saveAws")
    public  String saveFile(@RequestPart MultipartFile file,@RequestHeader String key) throws IOException {
        file.getBytes();
        awss3Service.putObject(key, file.getBytes());
        return "file successfully uploaded Key ::"+key;
    }

    @GetMapping("/fetchFile")
    public ResponseEntity<?> getFile(@RequestHeader String key){
      byte[] file=  awss3Service.getObject(key);
      if(file==null){
          return ResponseEntity.ok("No Files Found");
      }
      return ResponseEntity.ok(file);
    }


}
