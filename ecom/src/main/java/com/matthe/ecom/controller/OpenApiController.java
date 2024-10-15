package com.matthe.ecom.controller;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class OpenApiController {

    @Autowired
    private OpenAPI openAPI; // Autowire the OpenAPI bean

    @GetMapping("/save-openapi")
    public ResponseEntity<String> saveOpenApiSpecification() {
        try {
            // Convert the OpenAPI specification to a string (JSON format)
            String jsonSpec = openAPI.toString(); // Convert OpenAPI to JSON

            // Save the JSON string to a file named "openapi-spec.json"
            try (FileWriter fileWriter = new FileWriter("openapi-spec.json")) {
                fileWriter.write(jsonSpec);
            }

            // Return a successful response
            return ResponseEntity.ok("OpenAPI specification saved.");
        } catch (IOException e) {
            // Handle IO exceptions and return an error response
            return ResponseEntity.status(500).body("Failed to save OpenAPI specification: " + e.getMessage());
        }
    }
}
