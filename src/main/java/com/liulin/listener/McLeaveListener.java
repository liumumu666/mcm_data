package com.liulin.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class McLeaveListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("调用了用户事件监听器");
    }
}
