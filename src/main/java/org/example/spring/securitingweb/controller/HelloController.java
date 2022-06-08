package org.example.spring.securitingweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping
    public String helloPage() {
        return "hello";
    }
}
