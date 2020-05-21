package com.kp.securewithjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HelloRessource {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}