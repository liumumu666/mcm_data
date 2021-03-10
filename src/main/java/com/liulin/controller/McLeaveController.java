package com.liulin.controller;


import com.liulin.entity.McLeave;
import com.liulin.entity.McUser;
import com.liulin.service.McLeaveService;
import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class McLeaveController {

    @Autowired
    McLeaveService mcLeaveService;
    @Autowired
    ProcessEngine processEngine;
    public static String BUSINESSKEY=" ";


    /**
     * 申请了一个请假
     */
    @PostMapping("/task/addOneLeave")
    public Boolean addOneLeave(@RequestBody McLeave mcLeave) {
        //从前端获取用户填写的表单信息，插入到数据库中
        if (mcLeaveService.addOneLeave(mcLeave) == 1) {
            System.out.println("======请假表更新成功======");
            //启动流程，插入businessKey
            RuntimeService runtimeService = processEngine.getRuntimeService();

            //构造一个map存放条件
            HashMap<String, Object> map = new HashMap<>();
            map.put("mcLeave", mcLeave);

            //启动流程
            runtimeService.startProcessInstanceByKey(
                    "LeaveApplication"
                    , mcLeave.getMcLeaveId().toString()
                    , map);
            System.out.println("======成功启动流程======");
            //创建taskService
            TaskService taskService = processEngine.getTaskService();
            //根据流程key 和任务的负责人 查询任务
            Task task = taskService.createTaskQuery()
                    .processDefinitionKey("LeaveApplication")
                    .taskAssignee("staff")
                    .singleResult();
            //同时完成任务
            taskService.complete(task.getId());
            System.out.println("======成功完成申请的任务======");
            return true;
        } else
            return false;
    }

    /**
     * 查看申请的请假记录里
     *
     * @param request
     * @return
     */
    @GetMapping("/task/findMcLeavesByUser")
    public List<McLeave> findMcLeavesByUser(HttpServletRequest request) {
        System.out.println("McLeaveController.findMcLeavesByUser");


        McUser mcUser = (McUser) request.getSession().getAttribute("mcUser");

        return mcLeaveService.findMcLeavesByUser(mcUser.getMcUserId());

    }

    /**
     * 根据 businessKey 查看审核进度
     */


    @GetMapping("/task/findTaskByBusinessKey")
    public String findTask(Integer businessKey) {
        TaskService taskService = processEngine.getTaskService();

        System.out.println("用来查询任务进度的businessKey = " + businessKey);
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey.toString())
                .singleResult();

        System.out.println("查询到的任务信息 = " + task);
        System.out.println("================================");

        return task.toString();
    }


    /**
     * 根据用户id查看要完成的任务
     */

    @GetMapping("/task/findMyTask")
    public List<McLeave> findMyTask(HttpServletRequest request) {

        McUser mcUser = (McUser) request.getSession().getAttribute("mcUser");
        Integer userJob = mcUser.getMcUserJob();
        System.out.println(mcUser.getMcUserNiname() + "开始查看要审核的申请");

        //        2，获取taskService
        TaskService taskService = processEngine.getTaskService();
        //         根据job判断assignee
        String assignee = null;
        switch (userJob) {
            case 1:
                assignee = "zongjingli";
                break;
            case 2:
                assignee = "bumenjingli";
                break;
            case 3:
                assignee = "caiwu";
                break;
            default:
                assignee = "staff";
        }

//        3，根据流程key 和任务的负责人 查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("LeaveApplication")
                //.processDefinitionKey(definitionKey)
                .taskAssignee(assignee)
                .list();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<McLeave> leaves = new ArrayList<>();

        for (Task task : tasks) {
            String businessKey = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult().getBusinessKey();
            leaves.add(mcLeaveService.findMcLeaveByBusinessKey(businessKey));
        }
        return leaves;
    }

    /**
     * 根据 任务businessKey 完成任务
     */

    @GetMapping("/task/completeTaskByBusinessKey")
    public Boolean completeTaskByBusinessKey(String businessKey) {
        TaskService taskService = processEngine.getTaskService();
        BUSINESSKEY = businessKey;
        System.out.println(businessKey);
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();

        System.out.println("查询到的任务信息 = " + task);
        System.out.println("================================");

        System.out.println(task.getId());
        taskService.complete(task.getId());
        System.out.println("完成任务");


        return true;
    }


}
