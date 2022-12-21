package com.example.api.rest;

import com.example.TestBase;
import com.example.application.usecase.common.ArchComponentConnectionDTO;
import com.example.application.usecase.common.ArchComponentDTO;
import com.example.domain.analyze.command.UpdateArchitectureCommand;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchComponentConnection;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import com.example.domain.analyze.repository.ArchitectureRepository;
import com.example.domain.analyze.service.ArchSystemService;
import com.example.domain.analyze.service.ArchitectureService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArchSystemControllerTest extends TestBase {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    @Autowired
    private ArchitectureRepository architectureRepository;

    @Autowired
    private ArchSystemService archSystemService;

    @Autowired
    private ArchitectureService architectureService;

    private ArchComponentDTO buidSimpleArchComponentDTO(String id) {
        return ArchComponentDTO.builder()
                .id(id)
                .name(id + "-name")
                .type(ArchComponent.Type.MODULE)
                .build();
    }

    private ArchComponentConnectionDTO buildSimpleArchComponentConnectionDTO(ArchComponentDTO source,
                                                                             ArchComponentDTO target) {
        return ArchComponentConnectionDTO.builder()
                .source(source.getId())
                .target(target.getId())
                .build();
    }

    @Test
    void should_create_arch_system() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "any");

        mockMvc.perform(post("/arch-systems").contentType(APPLICATION_JSON).content(json.toJSONString()))
                .andExpect(status().isCreated());
    }

    @Test
    void should_get_arch_system() throws Exception {
        String id = archSystemService.create("any");

        mockMvc.perform(get("/arch-systems/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("any"));
    }
}