package com.example.po;

import com.example.domain.analyze.model.Architecture;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "architecture")
public class ArchitecturePO {
    private String archSystemId;

    @Id
    private String id;


    @Enumerated(EnumType.STRING)
    private Architecture.ArchStyle archStyle;
}
