package com.portfolio.controller;

import com.portfolio.model.Certification;
import com.portfolio.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @GetMapping
    public List<Certification> getAllCertifications() {
        return certificationService.getAllCertifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable Long id) {
        return certificationService.getCertificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Certification createCertification(@RequestBody Certification certification) {
        return certificationService.createCertification(certification);
    }

    @PutMapping("/{id}")
    public Certification updateCertification(@PathVariable Long id, @RequestBody Certification certification) {
        return certificationService.updateCertification(id, certification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }
}
