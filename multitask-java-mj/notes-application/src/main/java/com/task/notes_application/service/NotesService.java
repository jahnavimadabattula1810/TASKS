package com.task.notes_application.service;

import com.task.notes_application.entity.NotesEntity;
import com.task.notes_application.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class NotesService {

    private static final Logger log =
            LoggerFactory.getLogger(NotesService.class);

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<NotesEntity> getNotesByEmail(String email) {
        log.debug("Fetching notes for email: {}", email);
        return notesRepository.findByUserEmail(email);
    }

    public NotesEntity createNote(NotesEntity notesEntity) {
        notesEntity.setLastUpdated(LocalDateTime.now());
        log.info("Creating note for {}", notesEntity.getUserEmail());
        return notesRepository.save(notesEntity);
    }

    public NotesEntity updateNote(Long id, NotesEntity updatedNote) {
        NotesEntity notesEntity = notesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        notesEntity.setTitle(updatedNote.getTitle());
        notesEntity.setDescription(updatedNote.getDescription());
        notesEntity.setLastUpdated(LocalDateTime.now());

        return notesRepository.save(notesEntity);
    }

    public void deleteNote(Long id) {
        log.warn("Deleting note with id {}", id);
        notesRepository.deleteById(id);
    }
}

