package com.example.blogjpa.controller;

import com.example.blogjpa.external.ExternalApiParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonParseTestController {

    private final ExternalApiParser externalApiParser;

    public JsonParseTestController(ExternalApiParser externalApiParser) {
        this.externalApiParser = externalApiParser;
    }

    @GetMapping("/api/test")
    @ResponseBody
    public String test() {
        if (externalApiParser.parserAndSave()) {
            return "성공했습니다. <a href=\"/articles\">localhost:8080/articles</a> 로 이동하세요.";
        } else {
            return "실패했습니다";
        }
    }

}