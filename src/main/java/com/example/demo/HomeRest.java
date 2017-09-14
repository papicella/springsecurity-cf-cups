package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRest
{
    @Value("${spring.application.name:UNKNOWN}")
    private String applicationName;

    @RequestMapping ("/")
    public String home ()
    {
        return "Application Name : " + applicationName;
    }

}
