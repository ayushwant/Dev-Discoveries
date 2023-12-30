package com.example.demo.controller;

import com.example.demo.service.DynamicEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path="/demo")
public class DynamicEntityController {

    private final DynamicEntityService dynamicEntityService;

    @Autowired
    public DynamicEntityController(DynamicEntityService dynamicEntityService) {
        this.dynamicEntityService = dynamicEntityService;
    }

    @GetMapping("/generateEntities")
    public void generateEntities(@RequestParam String packageName) {
        dynamicEntityService.generateEntities(packageName);
    }
}
