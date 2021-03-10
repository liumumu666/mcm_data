package com.liulin.controller;

import com.liulin.entity.McUserJob;
import com.liulin.service.McUserJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class McUserJobController {

    @Autowired
    McUserJobService mcUserJobService;

    @GetMapping("/findJobs")
    public List<McUserJob> findAllJobs(){
        System.out.println("McUserJobController.findAllJobs");
        return mcUserJobService.findAllJobs();
    }


}
