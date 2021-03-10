package com.liulin.service;

import com.liulin.entity.McUserJob;
import com.liulin.mapper.McUserJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McUserJobService {

    @Autowired
    McUserJobMapper mcUserJobMapper;

    public List<McUserJob> findAllJobs(){
        System.out.println("McUserJobService.findAllJobs");
        return mcUserJobMapper.findAllJobs();
    };

}
