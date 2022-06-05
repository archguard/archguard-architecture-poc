package com.example.domain.archsystem.repository;

import com.example.domain.archsystem.model.ArchComponentConnection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchComponentConnectionPO {
    private String archSystemId;

    private String source;

    private String target;

    public static ArchComponentConnectionPO from(ArchComponentConnection archComponentConnection) {
        return ArchComponentConnectionPO.builder()
                .archSystemId(archComponentConnection.getArchSystemId())
                .source(archComponentConnection.getSource())
                .target(archComponentConnection.getTarget())
                .build();
    }

}
