package com.novo.personalproject.controller;

import com.novo.personalproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/resources/static")
public class StaticController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/css/{variablePart}")
    public ResponseEntity<String> getCss(@PathVariable String variablePart) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/css/" + variablePart);

        if (resource.exists()) {
            byte[] cssBytes = Files.readAllBytes(Path.of(resource.getURI()));
            String cssContent = new String(cssBytes);

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("text/css"))
                    .body(cssContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/js/{variablePart}")
    public ResponseEntity<String> getJavaScript(@PathVariable String variablePart) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/js/" + variablePart);

        if (resource.exists()) {
            byte[] jsBytes = Files.readAllBytes(Path.of(resource.getURI()));
            String JavaScriptContent = new String(jsBytes);

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("text/javascript"))
                    .body(JavaScriptContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/media/{variablePart:.+}")
    public ResponseEntity<byte[]> getMediaFile(@PathVariable String variablePart) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/media/" + variablePart);

        if (resource.exists() && resource.isReadable()) {
            byte[] content = Files.readAllBytes(resource.getFile().toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(content.length);
            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


