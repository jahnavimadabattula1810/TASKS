package com.task.notes_application.controller;

import com.task.notes_application.entity.ResidentEntity;
import com.task.notes_application.service.ResidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/residents")
@CrossOrigin(origins = "http://localhost:3000")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResidentEntity createResident(@RequestBody ResidentEntity residentEntity) {
        return residentService.createResident(residentEntity);
    }

    @GetMapping
    public Page<ResidentEntity> getResidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String flatNumber,
            @RequestParam(required = false) String rentStatus) {
        
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
            Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        if (name != null || state != null || gender != null || flatNumber != null || rentStatus != null) {
            return residentService.getResidentsByFilters(name, state, gender, flatNumber, rentStatus, pageable);
        }
        
        return residentService.getAllResidents(pageable);
    }

    @GetMapping("/{id}")
    public ResidentEntity getResidentById(@PathVariable Long id) {
        return residentService.getResidentById(id);
    }

    @PutMapping("/{id}")
    public ResidentEntity updateResident(@PathVariable Long id, @RequestBody ResidentEntity residentEntity) {
        return residentService.updateResident(id, residentEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);
    }
}

