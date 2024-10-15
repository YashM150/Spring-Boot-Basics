package com.matthe.ecom.controller;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiController {

    @Autowired
    private OpenAPI openAPI;

    @Autowired
    private YAMLMapper yamlMapper; // For YAML conversion

    @GetMapping("/openapi-spec-yaml")
    public ResponseEntity<String> getOpenApiSpecificationInYaml() {
        try {
            // Convert the OpenAPI object to a YAML string
            String yamlSpec = yamlMapper.writeValueAsString(openAPI);

            // Return the YAML specification as the response
            return ResponseEntity.ok(yamlSpec);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to generate OpenAPI YAML: " + e.getMessage());
        }
    }
}
