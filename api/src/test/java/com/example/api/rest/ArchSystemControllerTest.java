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
    void should_update_architecture() throws Exception {
        String id = archSystemService.create("any");

        JSONObject json = new JSONObject();
        json.put("archStyle", "LAYERED");
        ArchComponentDTO archComponentDTO1 = buidSimpleArchComponentDTO("id-1");
        ArchComponentDTO archComponentDTO2 = buidSimpleArchComponentDTO("id-2");
        json.put("archComponents", List.of(archComponentDTO1, archComponentDTO2));
        ArchComponentConnectionDTO archComponentConnectionDTO =
                buildSimpleArchComponentConnectionDTO(archComponentDTO1, archComponentDTO2);
        json.put("archComponentConnections", List.of(archComponentConnectionDTO));


        mockMvc.perform(
                        put("/arch-systems/" + id).contentType(APPLICATION_JSON).content(json.toJSONString()))
                .andExpect(status().isOk());

        Architecture architecture = architectureRepository.getByArchSystemId(id).get();
        assertThat(architecture).isNotNull();
        assertThat(architecture.getArchStyle()).isEqualTo(Architecture.ArchStyle.LAYERED);
        List<ArchComponent> archComponents = architectureRepository.getArchComponents(id);
        assertThat(archComponents).hasSize(2);
        List<ArchComponentConnection> archComponentConnections = architectureRepository.getArchComponentConnections(id);
        assertThat(archComponentConnections).hasSize(1);
    }

    @Test
    void should_get_arch_system() throws Exception {
        String id = archSystemService.create("any");

        mockMvc.perform(get("/arch-systems/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("any"));
    }

    @Test
    void should_get_arch_system_with_architecture() throws Exception {
        String id = archSystemService.create("any");

        ArchComponent archComponent1 = ArchComponent.build("id-1", "name-1", ArchComponent.Type.MODULE);
        ArchComponent archComponent2 = ArchComponent.build("id-2", "name-2", ArchComponent.Type.MODULE);
        ArchComponentConnection archComponentConnection =
                ArchComponentConnection.build(archComponent1.getId(), archComponent2.getId());
        UpdateArchitectureCommand updateArchitectureCommand = UpdateArchitectureCommand.builder()
                .archStyle(Architecture.ArchStyle.LAYERED)
                .archComponents(List.of(archComponent1, archComponent2))
                .archComponentConnections(List.of(archComponentConnection))
                .build();
        architectureService.create(id, updateArchitectureCommand);

        mockMvc.perform(get("/arch-systems/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("any"))
                .andExpect(jsonPath("$.architecture.archStyle").value(updateArchitectureCommand.getArchStyle().name()))
                .andExpect(jsonPath("$.architecture.archComponents", hasSize(2)))
                .andExpect(jsonPath("$.architecture.archComponentConnections", hasSize(1)));
    }
}