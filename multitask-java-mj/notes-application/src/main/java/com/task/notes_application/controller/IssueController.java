package com.task.notes_application.controller;

import com.task.notes_application.entity.IssueEntity;
import com.task.notes_application.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "http://localhost:3000")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public IssueEntity createIssue(@RequestBody IssueEntity issueEntity) {
        return issueService.createIssue(issueEntity);
    }

    @GetMapping
    public Page<IssueEntity> getIssues(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String title) {
        
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
            Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        IssueEntity.IssueCategory categoryEnum = null;
        if (category != null && !category.isEmpty()) {
            try {
                categoryEnum = IssueEntity.IssueCategory.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Invalid category, will be ignored
            }
        }
        
        if (email != null || categoryEnum != null || title != null) {
            return issueService.getIssuesByFilters(email, categoryEnum, title, pageable);
        }
        
        return issueService.getAllIssues(pageable);
    }

    @GetMapping("/{id}")
    public IssueEntity getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
    }
}

