package com.task.notes_application.service;

import com.task.notes_application.entity.IssueEntity;
import com.task.notes_application.repository.IssueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IssueService {

    private static final Logger log = LoggerFactory.getLogger(IssueService.class);

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public IssueEntity createIssue(IssueEntity issueEntity) {
        log.info("Creating support issue for email: {}", issueEntity.getUserEmail());
        return issueRepository.save(issueEntity);
    }

    public Page<IssueEntity> getAllIssues(Pageable pageable) {
        log.debug("Fetching all issues with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return issueRepository.findAll(pageable);
    }

    public Page<IssueEntity> getIssuesByFilters(String email, IssueEntity.IssueCategory category, String title, Pageable pageable) {
        log.debug("Fetching issues with filters: email={}, category={}, title={}", email, category, title);
        return issueRepository.findByFilters(email, category, title, pageable);
    }

    public IssueEntity getIssueById(Long id) {
        log.debug("Fetching issue with id: {}", id);
        return issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found with id: " + id));
    }

    public void deleteIssue(Long id) {
        log.warn("Deleting issue with id: {}", id);
        if (!issueRepository.existsById(id)) {
            throw new RuntimeException("Issue not found with id: " + id);
        }
        issueRepository.deleteById(id);
    }
}

