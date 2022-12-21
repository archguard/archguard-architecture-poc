package com.example.application;

import com.example.application.usecase.GetArchitectureCase;
import com.example.application.usecase.UpdateArchitectureCase;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.service.ArchitectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchitectureApplicationService {
    @Autowired
    private ArchitectureService architectureService;

    public void createArchitecture(String archSystemId, UpdateArchitectureCase.Request request) {
        architectureService.create(archSystemId, UpdateArchitectureCase.Request.to(request));
    }

    public GetArchitectureCase.Response getDetail(String archSystemId, String id) {
        Architecture architecture = architectureService.getByArchSystemId(archSystemId);
        return GetArchitectureCase.Response.from(architecture);
    }
}
