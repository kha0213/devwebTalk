package com.example.devwebtalk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 파일 처리 관련 컨트롤러
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-11
 * Time: 오후 7:46
 */
@RequestMapping("/file")
@Controller
public class FileController {

    @GetMapping("/download.do")
    public String download() {
        return null;
    }

    @GetMapping("/upload.do")
    public String upload() {
        return null;
    }
}
