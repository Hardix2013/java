package ru.mylife54.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String upload(MultipartFile file);
    void delete(String filename);
    String getFile(String name);
    MultipartFile[] getAllFile(String[] names);


}
