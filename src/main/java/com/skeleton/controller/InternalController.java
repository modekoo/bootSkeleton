package com.skeleton.controller;

import com.skeleton.dto.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalController {
    @GetMapping("/health")
    public ResponseEntity health() {
        return ResponseEntity.ok(new CommonResponse());
    }
}
