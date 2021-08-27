package com.example.devwebtalk.controller;

import com.example.devwebtalk.vo.FileDownloadVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String downloadView() {
        return "/file/download";
    }

    @PostMapping("/download.do")
    public @ResponseBody ResponseEntity<?> download(FileDownloadVO fileDownloadVO) {
        return null;
    }

    @GetMapping("/upload.do")
    public @ResponseBody String upload() {
        return null;
    }
}
