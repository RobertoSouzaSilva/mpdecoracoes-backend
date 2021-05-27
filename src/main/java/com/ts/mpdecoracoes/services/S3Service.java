package com.ts.mpdecoracoes.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URL uploadFile(MultipartFile file) {
        try {
            String originalName = file.getOriginalFilename();
            //String extension = FilenameUtils.getExtension(originalName);
            //String fileName = originalName.replace(" ", "_").toLowerCase(Locale.ROOT) + "." + extension;

            InputStream is = file.getInputStream();
            String contentType = file.getContentType();
            return uploadFile(is, originalName, contentType);

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    private URL uploadFile(InputStream is, String fileName, String contentType) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(contentType);
        System.out.println("upload start");
        s3Client.putObject(bucketName, fileName, is, meta);
        System.out.println("upload finish");

        return s3Client.getUrl(bucketName, fileName);
    }
}
