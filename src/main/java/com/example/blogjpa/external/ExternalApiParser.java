package com.example.blogjpa.external;

import com.example.blogjpa.service.ExternalApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
public class ExternalApiParser {

    private final ExternalApiService externalApiService;

    public ExternalApiParser(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    // 외부 API 호출 -> json 받아오기
    public boolean parserAndSave() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            List<LinkedHashMap<String, Object>> list = response.getBody();

            // title, body만 필요
            for (LinkedHashMap<String, Object> map : list) {
                String title = (String) map.get("title");
                String content = (String) map.get("body");

                externalApiService.saveArticle(title, content);
            }
            return true;
        }
        return false;
    }
}
