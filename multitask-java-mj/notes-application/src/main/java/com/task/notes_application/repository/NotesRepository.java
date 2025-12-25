package com.task.notes_application.repository;

import com.task.notes_application.entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<NotesEntity,Long> {
    List<NotesEntity> findByUserEmail(String userEmail);
}
