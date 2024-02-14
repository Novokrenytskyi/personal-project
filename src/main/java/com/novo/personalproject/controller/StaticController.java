package com.novo.personalproject.controller;

import com.novo.personalproject.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/resources/static")
public class StaticController {

    @Autowired
    private StaticService staticService;

    @GetMapping("/css/{fileName:.+\\.css}")
    public ResponseEntity<String> getCss(@PathVariable String fileName) {

        return staticService.get("css/" + fileName)
                .map(content -> ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("text/css"))
                        .body(new String(content)))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/js/{fileName:.+\\.js|.+\\.mjs}")
    public ResponseEntity<String> getJavaScript(@PathVariable String fileName) {
        return staticService.get("js/" + fileName)
                .map(content -> ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("text/javascript"))
                        .body(new String(content)))
                .orElseGet(ResponseEntity.notFound()::build);

    }

    @GetMapping("/media/{fileName:.+\\.png}")
    public ResponseEntity<byte[]> getMediaFile(@PathVariable String fileName) {

        return staticService.get("media/" + fileName)
                .map(content -> ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.IMAGE_PNG)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);

    }


}


