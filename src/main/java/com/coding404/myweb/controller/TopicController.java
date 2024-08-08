package com.coding404.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topic")
public class TopicController {

    //목록
    @GetMapping("/topicListAll")
    public String topicListAll() {
        return "topic/topicListAll";
    }

    //목록
    @GetMapping("/topicListMe")
    public String topicListMe() {
        return "topic/topicListMe";
    }

    //등록
    @GetMapping("/topicReg")
    public String topicReg() {
        return "topic/topicReg";
    }

    //수정
    @GetMapping("/topicModify")
    public String topicModify() {
        return "topic/topicModify";
    }

    //상세
    @GetMapping("/topicDetail")
    public String topicDetail() {
        return "topic/topicDetail";
    }

}
