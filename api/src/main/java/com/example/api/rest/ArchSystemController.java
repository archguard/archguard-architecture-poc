package com.example.api.rest;

import com.example.ArchSystemApplicationService;
import com.example.usecase.CreateArchSystemCase;
import com.example.usecase.GetArchSystemCase;
import com.example.usecase.UpdateArchitectureCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/arch-systems")
public class ArchSystemController {
    @Autowired
    private ArchSystemApplicationService applicationService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateArchSystemCase.Response createArchSystem(@RequestBody @Valid CreateArchSystemCase.Request request) {
        return applicationService.createArchSystem(request);
    }

    @PutMapping("/{id}")
    public void updateArchitecture(@PathVariable String id,
                                   @RequestBody @Valid UpdateArchitectureCase.Request request) {
        applicationService.updateArchitecture(id, request);
    }

    @GetMapping("/{id}")
    public GetArchSystemCase.Response getArchSystem(@PathVariable String id) {
        // todo query model
        return applicationService.getArchSystem(id);
    }
}
