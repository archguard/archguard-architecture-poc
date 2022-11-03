package com.example.usecase.common;

import com.example.domain.analyze.model.ArchComponentConnection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArchComponentConnectionDTO {
    private String source;

    private String target;

    public static ArchComponentConnectionDTO from(ArchComponentConnection archComponentConnection) {
        return ArchComponentConnectionDTO.builder()
                .source(archComponentConnection.getSource())
                .target(archComponentConnection.getTarget())
                .build();
    }
}
