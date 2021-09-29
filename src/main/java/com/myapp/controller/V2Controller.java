package com.myapp.controller;

import com.myapp.dto.Employee;
import com.myapp.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class V2Controller {

    @Autowired
    private RestService restService;


    @GetMapping("/getQuote")
    public String getQuote() {
        return restService.getQuote();
    }

}
