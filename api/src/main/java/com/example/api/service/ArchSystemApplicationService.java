package com.example.api.service;

import com.example.api.usecase.CreateArchSystemCase;
import com.example.api.usecase.GetArchSystemCase;
import com.example.api.usecase.UpdateArchitectureCase;
import com.example.domain.archsystem.model.ArchSystem;
import com.example.domain.archsystem.service.ArchSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UpdateArchitectureCase.Response updateArchitecture(String id, UpdateArchitectureCase.Request request) {
        ArchSystem archSystem = archSystemService.updateArchitecture(id, request.getArchStyle());

        return UpdateArchitectureCase.Response.from(archSystem);
    }
}
