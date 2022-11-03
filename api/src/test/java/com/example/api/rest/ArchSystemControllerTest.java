package com.example.api.rest;

import com.example.TestBase;
import com.example.domain.analyze.model.ArchComponent;
import com.example.domain.analyze.model.ArchSystem;
import com.example.domain.analyze.model.Architecture;
import com.example.domain.analyze.repository.ArchSystemRepository;
import com.example.domain.analyze.service.ArchSystemService;
import com.example.usecase.common.ArchComponentConnectionDTO;
import com.example.usecase.common.ArchComponentDTO;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArchSystemControllerTest extends TestBase {
    @Autowired
    private ArchSystemRepository archSystemRepository;

    @Autowired
    private ArchSystemService archSystemService;

    private ArchComponentDTO buidSimpleArchComponentDTO(String id) {
        ArchComponentDTO archComponentDTO = new ArchComponentDTO();
        archComponentDTO.setId(id);
        archComponentDTO.setName(id + "-name");
        archComponentDTO.setType(ArchComponent.Type.MODULE);
        return archComponentDTO;
    }

    private ArchComponentConnectionDTO buildSimpleArchComponentConnectionDTO(ArchComponentDTO source,
                                                                             ArchComponentDTO target) {
        ArchComponentConnectionDTO archComponentConnectionDTO = new ArchComponentConnectionDTO();
        archComponentConnectionDTO.setSource(source.getId());
        archComponentConnectionDTO.setTarget(target.getId());
        return archComponentConnectionDTO;
    }

    @Test
    void should_create_arch_system() throws Exception {
        JSONObject json = new JSONObject();
        json.put("name", "any");

        int count = archSystemRepository.findAll().size();

        mockMvc.perform(post("/arch-systems").contentType(APPLICATION_JSON).content(json.toJSONString()))
                .andExpect(status().isCreated());

        int updatedCount = archSystemRepository.findAll().size();

        assertThat(updatedCount - count).isEqualTo(1);

    }

    @Test
    void should_update_architecture() throws Exception {
        ArchSystem archSystem = archSystemService.create("any");

        JSONObject json = new JSONObject();
        json.put("archStyle", "LAYERED");
        ArchComponentDTO archComponentDTO1 = buidSimpleArchComponentDTO("id-1");
        ArchComponentDTO archComponentDTO2 = buidSimpleArchComponentDTO("id-2");
        json.put("archComponents", List.of(archComponentDTO1, archComponentDTO2));
        ArchComponentConnectionDTO archComponentConnectionDTO =
                buildSimpleArchComponentConnectionDTO(archComponentDTO1, archComponentDTO2);
        json.put("archComponentConnections", List.of(archComponentConnectionDTO));


        mockMvc.perform(
                        put("/arch-systems/" + archSystem.getId()).contentType(APPLICATION_JSON).content(json.toJSONString()))
                .andExpect(status().isOk());

        ArchSystem updatedArchSystem = archSystemRepository.getById(archSystem.getId()).get();
        Architecture architecture = updatedArchSystem.getArchitecture();
        assertThat(architecture).isNotNull();
        assertThat(architecture.getArchStyle()).isEqualTo(Architecture.ArchStyle.LAYERED);
        assertThat(architecture.getArchComponents()).hasSize(2);
        assertThat(architecture.getArchComponentConnections()).hasSize(1);
    }
}