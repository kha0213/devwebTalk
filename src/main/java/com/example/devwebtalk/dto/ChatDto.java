package com.example.devwebtalk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

/**
 * 2021-07-20
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Data
@AllArgsConstructor
public abstract class ChatDto {
    Long userId; // user Id
    String userName; // user name
    Long roomId; // user Id
    String content;

    public ChatDto(){}

    public ChatDto(String content) {
        this.content = content;
    }

    /**
     * HTML 태그 삭제 후 리턴
     */
    public ChatDto sendMessage() {
        content = HtmlUtils.htmlEscape(content);
        return this;
    }
}
