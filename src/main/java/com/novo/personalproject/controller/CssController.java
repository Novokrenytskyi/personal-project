package com.novo.personalproject.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/resources/static/css")
public class CssController {

    @GetMapping("/main.css")
    public ResponseEntity<String> getMainCss() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/css/main.css");

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

}
