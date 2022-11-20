package com.example.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "arch_system")
public class ArchSystemPO {
    @Id
    private String id;

    private String name;
}
