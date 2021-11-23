package com.credorax.poc.service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class MultiLevelController {

    @RequestMapping("postText1")
    public String postText1() {
        return "postText1";
    }

    @RequestMapping("posts/postText1")
    public String postText2() {
        return "postText2";
    }

    @RequestMapping("posts/internal/postText1")
    public String postText3() {
        return "postText3";
    }

    @RequestMapping("getText1")
    public String getText1() {
        return "postText1";
    }

    @RequestMapping("gets/getText1")
    public String getText2() {
        return "postText2";
    }

    @RequestMapping("gets/internal/getText1")
    public String getText3() {
        return "postText3";
    }

}
