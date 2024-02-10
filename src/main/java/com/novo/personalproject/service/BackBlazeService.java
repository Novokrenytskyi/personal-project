package com.novo.personalproject.service;

import com.backblaze.b2.client.B2Sdk;
import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentSource;
import com.backblaze.b2.client.contentSources.B2ContentTypes;
import com.backblaze.b2.client.contentSources.B2FileContentSource;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2FileVersion;
import com.backblaze.b2.client.structures.B2UploadFileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/*
@Service
@RequiredArgsConstructor
public class BackBlazeService {
    @Autowired
    private final B2StorageClient b2StorageClient;

    public String uploadFile(MultipartFile file) throws IOException {

        try (InputStream inputStream = file.getInputStream()) {
            B2Bucket bucketOrNullByName = b2StorageClient.getBucketOrNullByName("personal-project-images");
            String bucketId = bucketOrNullByName.getBucketId();

            String fileName = "test";

            String b2Auto = B2ContentTypes.B2_AUTO;

            B2ContentSource source = B2FileContentSource.build(file.getBytes());


            B2UploadFileRequest b2UploadFileRequest = B2UploadFileRequest.builder(bucketId,
                            fileName,
                            b2Auto,
                            source
                    )
                    .build();

            B2FileVersion fileVersion = b2StorageClient.uploadSmallFile(b2UploadFileRequest);
            return fileVersion.getFileId();

        } catch (B2Exception e) {
            throw new RuntimeException(e);
        }
    }
}
*/
