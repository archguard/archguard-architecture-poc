package com.example.api.rest;

import com.example.application.ArchSystemApplicationService;
import com.example.application.usecase.CreateArchSystemCase;
import com.example.application.usecase.GetArchSystemCase;
import com.example.application.usecase.UpdateArchitectureCase;
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

    @GetMapping("/{id}")
    public GetArchSystemCase.Response getArchSystem(@PathVariable String id) {
        return applicationService.getArchSystem(id);
    }
}
