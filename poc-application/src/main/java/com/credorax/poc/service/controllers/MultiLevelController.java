package com.credorax.poc.service.controllers;

import com.credorax.poc.service.configuration.Proxied;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
@Proxied
public class MultiLevelController {

    @PostMapping("postString")
    public String postText1(HttpServletRequest request) {
        return "postText1";
    }

    @PostMapping("postMap")
    public Map<String, Object> postText2(HttpServletRequest request) {
        return new HashMap<>();
    }

    @PostMapping("postList")
    public ResponseEntity<List<String>> postText3(HttpServletRequest request) {
        return ResponseEntity.of(Optional.of(new ArrayList<>()));
    }

    @PutMapping("postText1")
    public String putText1(HttpServletRequest request) {
        return "postText1";
    }

    @PutMapping("posts/postText1")
    public String putText2(HttpServletRequest request) {
        return "postText2";
    }

    @PutMapping("posts/internal/postText1")
    public String putText3(HttpServletRequest request) {
        return "postText3";
    }

    @GetMapping("postText1")
    public String getText1(HttpServletRequest request) {
        return "postText1";
    }

    @GetMapping("posts/postText1")
    public String getText2(HttpServletRequest request) {
        return "postText2";
    }

    @GetMapping("posts/internal/postText1")
    public String getText3(HttpServletRequest request) {
        return "postText3";
    }

}
