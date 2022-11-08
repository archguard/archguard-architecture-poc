package com.example.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ArchComponentConnectionPO {
    @Id
    private String id;

    private String source;

    private String target;
}
