package com.portfolio.controller;

import com.portfolio.model.Responsibility;
import com.portfolio.service.ResponsibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsibilities")
public class ResponsibilityController {

    @Autowired
    private ResponsibilityService responsibilityService;

    @GetMapping
    public List<Responsibility> getAllResponsibilities() {
        return responsibilityService.getAllResponsibilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsibility> getResponsibilityById(@PathVariable Long id) {
        return responsibilityService.getResponsibilityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Responsibility createResponsibility(@RequestBody Responsibility responsibility) {
        return responsibilityService.createResponsibility(responsibility);
    }

    @PutMapping("/{id}")
    public Responsibility updateResponsibility(@PathVariable Long id, @RequestBody Responsibility responsibility) {
        return responsibilityService.updateResponsibility(id, responsibility);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsibility(@PathVariable Long id) {
        responsibilityService.deleteResponsibility(id);
        return ResponseEntity.noContent().build();
    }
}
