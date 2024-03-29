package com.example.application;

import com.example.application.usecase.CreateArchSystemCase;
import com.example.application.usecase.GetArchSystemCase;
import com.example.domain.archsystem.ArchSystem;
import com.example.domain.archsystem.ArchSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchSystemApplicationService {
    @Autowired
    private ArchSystemService archSystemService;

    public CreateArchSystemCase.Response createArchSystem(CreateArchSystemCase.Request request) {
        String id = archSystemService.create(request.getName());

        return CreateArchSystemCase.Response.from(id);
    }

    public GetArchSystemCase.Response getArchSystem(String id) {
        ArchSystem archSystem = archSystemService.getById(id);

        return GetArchSystemCase.Response.from(archSystem);
    }
}
