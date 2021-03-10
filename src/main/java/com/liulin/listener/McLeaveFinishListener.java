package com.liulin.listener;

import com.liulin.controller.McLeaveController;
import com.liulin.service.McLeaveService;
import org.activiti.engine.*;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


public class McLeaveFinishListener implements ExecutionListener {
//    @Autowired
//
//    McLeaveService mcLeaveService;


    @Override
    @PostConstruct
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("事件结束，调用结束的流程监听器");
        String businessKey = McLeaveController.BUSINESSKEY;
        McLeaveService mcLeaveService =SpringUtil.getObject(McLeaveService.class);
        Integer state =  mcLeaveService.updateFinish(businessKey);
        System.out.println("===== 更新的状态是"+state+"=====");
    }


}
