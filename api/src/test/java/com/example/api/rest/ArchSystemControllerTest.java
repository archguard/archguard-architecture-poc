package com.example.api.rest;

import com.example.TestBase;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.repository.ArchSystemRepository;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArchSystemControllerTest extends TestBase {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    @Test
    void should_create_arch_system() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "any");

        mockMvc.perform(post("/arch-systems").contentType(APPLICATION_JSON).content(json.toJSONString()))
                .andExpect(status().isCreated());

        List<ArchSystem> archSystems = archSystemRepository.findAll();

        assertThat(archSystems).hasSize(1);

    }
}