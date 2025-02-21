package com.example.framework_test.controller;

import com.example.framework_test.bean.RequestScopedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-scope")
public class ControllerWithRequestScopedBean {

    @Autowired
    private RequestScopedBean requestScopedBean;

    @GetMapping
    public String getRequestScopeBeanId() {
        return requestScopedBean.getId();
    }
}
