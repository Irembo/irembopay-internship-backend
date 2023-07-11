package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.SplitParticipant;
import com.irembo.portal.repository.SplitParticipantRepository;

@RestController
@RequestMapping("/api/split-participant")
public class SplitParticipantController {
    private final SplitParticipantRepository splitParticipantRepository;

    public SplitParticipantController(SplitParticipantRepository splitParticipantRepository) {
        this.splitParticipantRepository = splitParticipantRepository;
    }

    @GetMapping
    public Page<SplitParticipant> getAllSplitParticipants(Pageable pageable) {
        return splitParticipantRepository.findAll(pageable);
    }
}

