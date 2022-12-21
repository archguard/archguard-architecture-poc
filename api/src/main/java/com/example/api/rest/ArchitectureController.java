package com.example.api.rest;

import javax.validation.Valid;
import com.example.api.contextholder.ArchSystemContextHolder;
import com.example.application.ArchitectureApplicationService;
import com.example.application.usecase.GetArchitectureCase;
import com.example.application.usecase.UpdateArchitectureCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/architectures")
public class ArchitectureController {
    @Autowired
    private ArchitectureApplicationService applicationService;

    @PostMapping
    public void updateArchitecture(@RequestBody @Valid UpdateArchitectureCase.Request request) {
        String archSystemId = ArchSystemContextHolder.getContext();
        applicationService.createArchitecture(archSystemId, request);
    }

    @GetMapping("/{id}")
    public GetArchitectureCase.Response getDetail(@PathVariable String id) {
        String archSystemId = ArchSystemContextHolder.getContext();
        return applicationService.getDetail(archSystemId, id);
    }
}
