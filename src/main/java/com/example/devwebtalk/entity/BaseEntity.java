package com.example.devwebtalk.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 2021-07-12
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
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
    @Column(columnDefinition = "varchar(1) default 'Y'")
    private char useYn;
    @Column(columnDefinition = "varchar(1) default 'N'")
    private char delYn;
}
