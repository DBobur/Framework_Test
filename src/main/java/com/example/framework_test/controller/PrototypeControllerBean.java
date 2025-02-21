package com.example.framework_test.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/prototype-controller")
@Scope("prototype")
public class PrototypeControllerBean {

    private final String id = UUID.randomUUID().toString();

    @GetMapping
    public String getControllerId() {
        return id;
    }
}
