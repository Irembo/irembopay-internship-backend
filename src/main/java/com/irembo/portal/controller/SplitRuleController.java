package com.irembo.portal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.SplitRule;
import com.irembo.portal.repository.SplitRuleRepository;

@RestController
@RequestMapping("/api/split-rule")
public class SplitRuleController {
    private final SplitRuleRepository splitRuleRepository;

    public SplitRuleController(SplitRuleRepository splitRuleRepository) {
        this.splitRuleRepository = splitRuleRepository;
    }

    @GetMapping
    public Page<SplitRule> getAllSplitRules(Pageable pageable) {
        return splitRuleRepository.findAll(pageable);
    }
}

