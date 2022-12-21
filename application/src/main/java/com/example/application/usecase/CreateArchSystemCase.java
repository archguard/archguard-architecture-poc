package com.example.application.usecase;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


public class CreateArchSystemCase {
    @Data
    public static class Request {
        @NotBlank
        private String name;
    }

    @Data
    @Builder
    public static class Response {
        private String id;

        public static Response from(String id) {
            return Response.builder()
                    .id(id)
                    .build();
        }
    }
}
