package com.portfolio.controller;

import com.portfolio.model.TechnologyTopicRate;
import com.portfolio.service.TechnologyTopicRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technology-topic-rates")
public class TechnologyTopicRateController {

    @Autowired
    private TechnologyTopicRateService technologyTopicRateService;

    @GetMapping
    public List<TechnologyTopicRate> getAllTechnologyTopicRates() {
        return technologyTopicRateService.getAllTechnologyTopicRates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnologyTopicRate> getTechnologyTopicRateById(@PathVariable Long id) {
        return technologyTopicRateService.getTechnologyTopicRateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public TechnologyTopicRate createTechnologyTopicRate(@RequestBody TechnologyTopicRate technologyTopicRate) {
        return technologyTopicRateService.createTechnologyTopicRate(technologyTopicRate);
    }

    @PutMapping("/{id}")
    public TechnologyTopicRate updateTechnologyTopicRate(@PathVariable Long id, @RequestBody TechnologyTopicRate technologyTopicRate) {
        return technologyTopicRateService.updateTechnologyTopicRate(id, technologyTopicRate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnologyTopicRate(@PathVariable Long id) {
        technologyTopicRateService.deleteTechnologyTopicRate(id);
        return ResponseEntity.noContent().build();
    }
}
