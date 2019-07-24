package ru.mylife54.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mylife54.services.StorageService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String upload(MultipartFile file) {
        String fileName = "";
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (file != null && !file.getOriginalFilename().isEmpty()){
            fileName = new StringBuilder().append(UUID.randomUUID().toString()).append(".").append(file.getOriginalFilename()).toString();
            try {
                file.transferTo(new File(uploadPath+"/"+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    @Override
    public void delete(String filename) {
        if(filename!=null){
            File file = new File(uploadPath+"/"+filename);
            if (file.exists()){
                file.delete();
            }
        }
    }

    @Override
    public String getFile(String name) {
        return uploadPath+"/"+name;
    }

    @Override
    public MultipartFile[] getAllFile(String[] names) {
        return new MultipartFile[0];
    }
}
