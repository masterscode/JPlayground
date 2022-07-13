package com.play.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreatedDate
    private LocalDateTime applicationDate;
    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User createdBy;

    @LastModifiedBy
    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User updatedBy;
}
