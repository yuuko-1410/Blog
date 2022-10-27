package com.yuuko.MyBlog.service.common;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileUploadService {
    String upload(MultipartFile files){
        return files.getName();
    }
}
