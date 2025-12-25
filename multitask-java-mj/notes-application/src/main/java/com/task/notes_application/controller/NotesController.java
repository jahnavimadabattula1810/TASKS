package com.task.notes_application.controller;

import com.task.notes_application.entity.NotesEntity;
import com.task.notes_application.service.NotesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/{email}")
    public List<NotesEntity> getNotes(@PathVariable String email) {
        return notesService.getNotesByEmail(email);
    }

    @PostMapping
    public NotesEntity create(@RequestBody NotesEntity notesEntity) {
        return notesService.createNote(notesEntity);
    }

    @PutMapping("/{id}")
    public NotesEntity update(@PathVariable Long id, @RequestBody NotesEntity notesEntity) {
        return notesService.updateNote(id, notesEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notesService.deleteNote(id);
    }
}

