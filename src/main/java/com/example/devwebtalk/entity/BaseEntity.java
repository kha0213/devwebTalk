package com.example.devwebtalk.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * 2021-07-12
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(columnDefinition = "varchar(255) default 'SYSTEM'")
    private String createBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    @Column(columnDefinition = "varchar(255) default 'SYSTEM'")
    private String lastModifiedBy;
    @Column(columnDefinition = "varchar(1) default 'N'")
    private char delYn;
}
