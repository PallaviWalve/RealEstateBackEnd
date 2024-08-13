package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.realestate.entity.Builder;
import com.example.realestate.service.BuilderService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/builders")
public class BuilderController {

    @Autowired
    private BuilderService builderService;

    @PostMapping
    public ResponseEntity<Builder> addBuilder(@RequestBody Builder builder) {
        Builder newBuilder = builderService.addBuilder(builder);
        return ResponseEntity.ok(newBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Builder> getBuilderById(@PathVariable Long id) {
        Builder builder = builderService.findById(id);
        return ResponseEntity.ok(builder);
    }

    @GetMapping
    public ResponseEntity<List<Builder>> getAllBuilders() {
        List<Builder> builders = builderService.findAll();
        return ResponseEntity.ok(builders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Builder> updateBuilder(@PathVariable Long id, @RequestBody Builder updatedBuilder) {
        Builder builder = builderService.updateBuilder(id, updatedBuilder);
        return ResponseEntity.ok(builder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilder(@PathVariable Long id) {
        builderService.deleteBuilder(id);
        return ResponseEntity.noContent().build();
    }
}
