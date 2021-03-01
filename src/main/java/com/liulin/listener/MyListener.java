package com.liulin.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("调用了监听器");
        delegateTask.setAssignee("张三");
    }
}
