package com.task.notes_application.repository;

import com.task.notes_application.entity.IssueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    
    Page<IssueEntity> findAll(Pageable pageable);
    
    @Query("SELECT i FROM IssueEntity i WHERE " +
           "(:email IS NULL OR i.userEmail LIKE %:email%) AND " +
           "(:category IS NULL OR i.category = :category) AND " +
           "(:title IS NULL OR i.title LIKE %:title%)")
    Page<IssueEntity> findByFilters(
        @Param("email") String email,
        @Param("category") IssueEntity.IssueCategory category,
        @Param("title") String title,
        Pageable pageable
    );
}

