package com.play.controllers;

import com.play.exceptions.GenericException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/upload")
@RestController
public class ImageUploadController {

    private final RestTemplate httpClient = new RestTemplate();

    @PostMapping
    public ResponseEntity<String> sendImageToAnotherService(@RequestPart("file") MultipartFile file) {
        try {
            var fileToUpload = file.getResource();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            LinkedMultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            data.add("file", fileToUpload);

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<LinkedMultiValueMap<String, Object>>(data, httpHeaders);

            return httpClient.exchange("http://localhost:9000/upload/receive", HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receivingService(@RequestPart("file") MultipartFile file) {
        return file == null ?
                ResponseEntity.badRequest().body("File not sent")
                : ResponseEntity.ok("File received " + file.getOriginalFilename());
    }

    public ResponseEntity<String> sendImageToAnotherService(@RequestPart("file") List<MultipartFile> multipartFiles) {
        var files = multipartFiles.stream().map(MultipartFile::getResource).collect(Collectors.toList());
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap();
        try {
            var resource = new FileSystemResource(multipartFiles.get(0).getResource().getFile());
        }catch (IOException ignored){}
        requestEntity.add("files", files);

        return ResponseEntity.ok("");
    }
}
