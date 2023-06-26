package com.irembo.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irembo.portal.model.SplitRule;
import com.irembo.portal.repository.SplitRuleRepository;

@RestController
@RequestMapping("/api")
public class SplitRuleController {
    private final SplitRuleRepository splitRuleRepository;

    @Autowired
    public SplitRuleController(SplitRuleRepository splitRuleRepository) {
        this.splitRuleRepository = splitRuleRepository;
    }

    @GetMapping("/split-rules")
    public Page<SplitRule> getAllSplitRules(Pageable pageable) {
        return splitRuleRepository.findAll(pageable);
    }
}

