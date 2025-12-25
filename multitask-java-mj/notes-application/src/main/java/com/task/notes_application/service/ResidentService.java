package com.task.notes_application.service;

import com.task.notes_application.entity.ResidentEntity;
import com.task.notes_application.repository.ResidentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResidentService {

    private static final Logger log = LoggerFactory.getLogger(ResidentService.class);

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public ResidentEntity createResident(ResidentEntity residentEntity) {
        log.info("Creating resident: {}", residentEntity.getName());
        return residentRepository.save(residentEntity);
    }

    public Page<ResidentEntity> getAllResidents(Pageable pageable) {
        log.debug("Fetching all residents with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return residentRepository.findAll(pageable);
    }

    public Page<ResidentEntity> getResidentsByFilters(String name, String state, String gender, String flatNumber, String rentStatus, Pageable pageable) {
        log.debug("Fetching residents with filters: name={}, state={}, gender={}, flatNumber={}, rentStatus={}", 
                  name, state, gender, flatNumber, rentStatus);
        return residentRepository.findByFilters(name, state, gender, flatNumber, rentStatus, pageable);
    }

    public ResidentEntity getResidentById(Long id) {
        log.debug("Fetching resident with id: {}", id);
        return residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found with id: " + id));
    }

    public ResidentEntity updateResident(Long id, ResidentEntity updatedResident) {
        log.info("Updating resident with id: {}", id);
        ResidentEntity residentEntity = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found with id: " + id));

        residentEntity.setName(updatedResident.getName());
        residentEntity.setState(updatedResident.getState());
        residentEntity.setGender(updatedResident.getGender());
        residentEntity.setFlatNumber(updatedResident.getFlatNumber());
        residentEntity.setRentStatus(updatedResident.getRentStatus());

        return residentRepository.save(residentEntity);
    }

    public void deleteResident(Long id) {
        log.warn("Deleting resident with id: {}", id);
        if (!residentRepository.existsById(id)) {
            throw new RuntimeException("Resident not found with id: " + id);
        }
        residentRepository.deleteById(id);
    }
}

