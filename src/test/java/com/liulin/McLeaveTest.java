package com.liulin;

import com.liulin.entity.McLeave;
import com.liulin.mapper.McLeaveMapper;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class McLeaveTest {
    @Autowired
    McLeaveMapper mcLeaveMapper;





    @Test
    public void startLeave(){
        McLeave mcLeave = new McLeave();

        mcLeave.setMcLeaveDay(3);
        mcLeave.setMcLeaveUserId(1);
        mcLeave.setMcLeaveName("回家请假");
        mcLeave.setMcLeaveStartTime(new Date(2020-1-2));
        mcLeave.setMcLeaveReason("back home");


        System.out.println("返回的id为:"+mcLeaveMapper.addLeave(mcLeave));
        System.out.println(mcLeave.getMcLeaveId());

    }


    /**
     * 通过businessKey查询任务
     */
    @Test
    public void findByBusinessKey(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceBusinessKey("1002")
//                .singleResult();
//
//        System.out.println("实例id = "+processInstance.getProcessInstanceId());

//        TaskService taskService = processEngine.getTaskService();
//
//        Task task = taskService.createTaskQuery()
//                //.processDefinitionKey(processInstance.getProcessInstanceId())
//                .processInstanceBusinessKey("1002")
//                .singleResult();
        //1,使用任务ID，查询对象task
        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery().taskId("43f5cd45-7f22-11eb-8f64-04ea565d5d6e").singleResult();
        //2.使用任务ID，获取实例ID
        String processInstanceId = task.getProcessInstanceId();
        //3.使用流程实例，查询
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //4.使用流程实例对象获取BusinessKey
        String business_key = pi.getBusinessKey();
        System.out.println(business_key);


    }
}
