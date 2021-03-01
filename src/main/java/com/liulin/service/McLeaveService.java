package com.liulin.service;

import com.liulin.entity.McLeave;
import com.liulin.mapper.McLeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McLeaveService {
    @Autowired
    McLeaveMapper mcLeaveMapper;

    public List<McLeave> findMcLeave(McLeave mcLeave) {
        System.out.println("McLeaveService.findMcLeave");

        return mcLeaveMapper.findMcLeaveByFinish(mcLeave);

    }
}
