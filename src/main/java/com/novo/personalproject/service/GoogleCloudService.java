package com.novo.personalproject.service;

import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class GoogleCloudService {
    @Value("${google-cloud.bucket-name}")
    private final String bucketName;

    @Value("${google-cloud.public-url}")
    private final String publicUrl;

    @Autowired
    private final Storage storage;

    @SneakyThrows
    public String uploadFile(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, fileName);

        String contentType = multipartFile.getContentType();
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(contentType)
                .build();

        try (InputStream is = multipartFile.getInputStream()) {
            storage.createFrom(blobInfo, is);

            return publicUrl + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
