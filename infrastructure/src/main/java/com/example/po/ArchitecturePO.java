package com.example.po;

import com.example.domain.analyze.model.Architecture;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "architecture")
public class ArchitecturePO {
    @Id
    private String archSystemId;

    private Architecture.ArchStyle archStyle;
}
