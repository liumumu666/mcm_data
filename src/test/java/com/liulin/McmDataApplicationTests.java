package com.liulin;

import com.liulin.entity.McLeave;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class McmDataApplicationTests {

    @Test
    void contextLoads() {

    }

    /**
     * 部署工作流
     */
    @Test
    public void testDeployment(){
//        1,创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2,获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        3，使用service进行流程的部署，定义一个名字，把bpmn和png部署到数据中心
        Deployment deploy = repositoryService.createDeployment()
                .name("请假申请流程")
                .key("chuchai")
                .addClasspathResource("processes/LeaveApplication.bpmn")
                //.addClasspathResource("bpmn/activityDemo1.svg")
                .deploy();
//        4，熟出部署信息
        System.out.println("流程部署id="+deploy.getId());
        System.out.println("流程部署key="+deploy.getKey());
        System.out.println("流程部署名字="+deploy.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess(){
//        1,创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2，获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        3,根据流程定义的id启动流程
        McLeave mcLeave = new McLeave();
        mcLeave.setMcLeaveDay(6);
        HashMap<String, Object> map = new HashMap<>();
        map.put("mcLeave",mcLeave);
        ProcessInstance instance = runtimeService
                .startProcessInstanceByKey("LeaveApplication","1001",map);
//        4，输出内容
        System.out.println("流程定义ID="+instance.getProcessDefinitionId());
        System.out.println("流程实例的ID="+instance.getId());
        System.out.println("当前活动的ID="+instance.getActivityId());
    }
    /**
     * 查询个人待执行的任务
     */

    @Test
    public void testFindPersonTaskList(){
//        1,获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2，获取taaskService
        TaskService taskService = processEngine.getTaskService();
//        3，根据流程key 和任务的负责人 查询任务
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("LeaveApplication")
                .taskAssignee("zhangjingli")
                .list();
        System.out.println(tasks);
//        4，输出
        for (Task task: tasks) {
            System.out.println("流程实例ID="+task.getProcessInstanceId());
            System.out.println("任务ID="+task.getId());
            System.out.println("任务负责人="+task.getAssignee());
            System.out.println("任务名称="+task.getName());
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask(){
//        1,获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2，获取taskService
        TaskService taskService = processEngine.getTaskService();
//        3，根据任务id完成任务
        taskService.complete("19a7a2d9-7a54-11eb-acdc-04ea565d5d6e");

//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("activityDemo1")
//                .taskAssignee("财务")
//                .singleResult();
//
//        System.out.println("流程实例ID"+task.getProcessInstanceId());
//        System.out.println("任务ID"+task.getId());
//        System.out.println("任务负责人"+task.getAssignee());
//        System.out.println("任务名称"+task.getName());
//        taskService.complete(task.getId());
    }


    /**
     * 删除部署的流程
     */
    @Test
    public void deleteDeployment(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //repositoryService.deleteDeployment("部署的ID",true);//true开启级联删除
        repositoryService.deleteDeployment("969000e9-7a3f-11eb-a586-04ea565d5d6e",true);//true开启级联删除
    }


}
