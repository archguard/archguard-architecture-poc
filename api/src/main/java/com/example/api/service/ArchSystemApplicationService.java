package com.example.api.service;

import com.example.api.usecase.CreateArchSystemCase;
import com.example.api.usecase.GetArchSystemCase;
import com.example.api.usecase.UpdateArchitectureCase;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.service.ArchSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchSystemApplicationService {
    @Autowired
    private ArchSystemService archSystemService;

    public CreateArchSystemCase.Response create(CreateArchSystemCase.Request request) {
        ArchSystem archSystem = archSystemService.create(request.getName());

        return CreateArchSystemCase.Response.from(archSystem);
    }

    public GetArchSystemCase.Response getDetail(String id) {
        ArchSystem archSystem = archSystemService.get(id);

        return GetArchSystemCase.Response.from(archSystem);
    }

    public void updateArchitecture(String id, UpdateArchitectureCase.Request request) {
        List<ArchComponent> archComponents = request.getArchComponents().stream()
                .map(archComponentRequest -> ArchComponent.build(
                        id, archComponentRequest.getName(), archComponentRequest.getArchComponentType())
                )
                .collect(Collectors.toList());

        List<ArchComponentConnection> archComponentConnections = request.getArchComponentConnections().stream()
                .map(archComponentConnectionRequest -> ArchComponentConnection
                        .build(id, archComponentConnectionRequest.getSource(),
                                archComponentConnectionRequest.getTarget()))
                .collect(Collectors.toList());

        ArchSystem archSystem = archSystemService
                .updateArchitecture(id, request.getArchStyle(), archComponents, archComponentConnections);
    }
}
