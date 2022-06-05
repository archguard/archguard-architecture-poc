package com.example.api.usecase;

import com.example.api.usecase.common.ArchComponentConnectionRequest;
import com.example.api.usecase.common.ArchComponentRequest;
import com.example.domain.archsystem.model.Architecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class UpdateArchitectureCase {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private Architecture.ArchStyle archStyle;
        private List<ArchComponentRequest> archComponents;
        private List<ArchComponentConnectionRequest> archComponentConnections;
    }
}
