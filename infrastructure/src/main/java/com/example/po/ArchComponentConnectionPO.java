package com.example.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "arch_component_connection")
public class ArchComponentConnectionPO {
    private String archSystemId;

    @Id
    private String id;

    private String source;

    private String target;

    public boolean equals(String archSystemId, String source, String target) {
        return this.archSystemId.equals(archSystemId) && this.source.equals(source) && this.target.equals(target);
    }
}
