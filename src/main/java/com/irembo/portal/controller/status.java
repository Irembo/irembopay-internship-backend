package com.irembo.portal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class status {

    @RequestMapping("/")
    public String index() {
        return "Irembo Portal is up and running!";
    }

}
