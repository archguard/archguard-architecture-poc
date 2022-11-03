package com.example;

import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.service.ArchSystemService;
import com.example.usecase.CreateArchSystemCase;
import com.example.usecase.GetArchSystemCase;
import com.example.usecase.UpdateArchitectureCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchSystemApplicationService {
    @Autowired
    private ArchSystemService archSystemService;

    public CreateArchSystemCase.Response createArchSystem(CreateArchSystemCase.Request request) {
        ArchSystem archSystem = archSystemService.create(request.getName());

        return CreateArchSystemCase.Response.from(archSystem);
    }

    public void updateArchitecture(String id, UpdateArchitectureCase.Request request) {
        archSystemService.updateArchitecture(id, UpdateArchitectureCase.Request.to(request));
    }

    public GetArchSystemCase.Response getArchSystem(String id) {
        ArchSystem archSystem = archSystemService.getById(id);

        return GetArchSystemCase.Response.from(archSystem);
    }
}
