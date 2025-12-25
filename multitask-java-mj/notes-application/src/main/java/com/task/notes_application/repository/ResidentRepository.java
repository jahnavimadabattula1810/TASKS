package com.task.notes_application.repository;

import com.task.notes_application.entity.ResidentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidentRepository extends JpaRepository<ResidentEntity, Long> {
    
    Page<ResidentEntity> findAll(Pageable pageable);
    
    @Query("SELECT r FROM ResidentEntity r WHERE " +
           "(:name IS NULL OR r.name LIKE %:name%) AND " +
           "(:state IS NULL OR r.state LIKE %:state%) AND " +
           "(:gender IS NULL OR r.gender LIKE %:gender%) AND " +
           "(:flatNumber IS NULL OR r.flatNumber LIKE %:flatNumber%) AND " +
           "(:rentStatus IS NULL OR r.rentStatus LIKE %:rentStatus%)")
    Page<ResidentEntity> findByFilters(
        @Param("name") String name,
        @Param("state") String state,
        @Param("gender") String gender,
        @Param("flatNumber") String flatNumber,
        @Param("rentStatus") String rentStatus,
        Pageable pageable
    );
}

