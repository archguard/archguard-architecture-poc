package com.example.api.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.TestBase;
import com.example.domain.archsystem.ArchSystemService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ArchSystemControllerTest extends TestBase {

    @Autowired
    private ArchSystemService archSystemService;

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