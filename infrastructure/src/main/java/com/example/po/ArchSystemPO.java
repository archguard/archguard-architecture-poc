package com.example.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ArchSystemPO {
    @Id
    private String id;
    private String name;
}
