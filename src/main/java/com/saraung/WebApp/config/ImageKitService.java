package com.saraung.WebApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
public class ImageKitService {

    @Value("${imagekit.private.key}")
    private String privateKey;

    @Value("${imagekit.url.endpoint}")
    private String urlEndpoint;

    public String upload(MultipartFile file) {
        try {
            // Basic auth: privateKey:
            String auth = Base64.getEncoder()
                    .encodeToString((privateKey + ":").getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + auth);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });
            body.add("fileName", file.getOriginalFilename());
            body.add("folder", "/products");

            HttpEntity<MultiValueMap<String, Object>> request =
                    new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://upload.imagekit.io/api/v1/files/upload",
                    request,
                    Map.class
            );

            return (String) response.getBody().get("url");

        } catch (Exception e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }
}
