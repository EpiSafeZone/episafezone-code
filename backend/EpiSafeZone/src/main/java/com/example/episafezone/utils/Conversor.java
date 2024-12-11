package com.example.episafezone.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Conversor {
    private final static String IMAGE_DIR = "src/main/resources/static/profileImages";

    public static String handleFileUpload(MultipartFile multipartFile) {
        String newFileName = "";
        try{
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = multipartFile.getBytes();
            String fileOriginalName = multipartFile.getOriginalFilename();

            long filesize = multipartFile.getSize();
            long maxSize =  5 * 1024 * 1024;
            if(filesize > maxSize){return "file size must be les than 5MB";}

            if(fileOriginalName.endsWith(".jpg") || fileOriginalName.endsWith(".jpeg") || fileOriginalName.endsWith(".png")){
                String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                newFileName = fileName + fileExtension;
                File folder = new File(IMAGE_DIR);
                if(!folder.exists()){
                    folder.mkdirs();
                }
                Path path = Paths.get(IMAGE_DIR + "/" + newFileName);
                Files.write(path,bytes);
            }{throw new }
        } catch (Exception e){
            e.printStackTrace();
        }
        return newFileName;
    }
}
