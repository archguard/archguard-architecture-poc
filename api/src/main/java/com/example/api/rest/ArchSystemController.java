package com.example.api.rest;

import com.example.api.service.ArchSystemApplicationService;
import com.example.api.usecase.CreateArchSystemCase;
import com.example.api.usecase.GetArchSystemCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public CreateArchSystemCase.Response create(@RequestBody @Valid CreateArchSystemCase.Request request) {
        return applicationService.create(request);
    }

    @GetMapping("/{id}")
    public GetArchSystemCase.Response getDetail(@PathVariable String id) {
        return applicationService.getDetail(id);
    }
}
