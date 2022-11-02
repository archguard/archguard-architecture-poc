package com.example.domain.analyze.model;

import com.example.domain.common.Entity;
import lombok.Getter;

@Getter
@Entity
public class ArchComponentConnection {
    private String source;

    private String target;
}
