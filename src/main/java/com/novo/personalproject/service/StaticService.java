package com.novo.personalproject.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaticService {

    @Value("${app.image.bucket:src/main/resources/static/}")
    private final String bucket;

    @SneakyThrows
    public Optional<byte[]> get(String resource) {
        Path fullImagePath = Path.of(bucket, resource);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }

}
