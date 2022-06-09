package org.example.spring.securitingweb.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasAuthority('read')")
    public String helloPage() {
        return "hello";
    }
}
