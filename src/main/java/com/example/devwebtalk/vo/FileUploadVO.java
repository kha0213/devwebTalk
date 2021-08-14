package com.example.devwebtalk.vo;

import com.example.devwebtalk.entity.type.FileType;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.attribute.FileTime;
import java.util.UUID;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-11
 * Time: 오후 7:48
 */
@Data
public class FileUploadVO {
    // 올린 파일 이름
    private String uploadFileName;
    // 저장소 파일 이름 (UUID + 확장자)
    private String storeFileName;
    // IMAGE, VIDEO, ATTACH
    private FileType fileType;
    // 확장자
    private String extension;
    // 파일 크기
    private Long size;

    public FileUploadVO(String uploadFileName, String storeFileName, Long size) {
        this.uploadFileName = uploadFileName;
        this.size = size;
        this.extension = FilenameUtils.getExtension(uploadFileName);
        this.storeFileName = UUID.randomUUID().toString() + extension;
    }
}
