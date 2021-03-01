package com.liulin.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class McLeaveListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println(delegateTask.getAssignee());
        System.out.println(delegateTask.getCandidates());
        System.out.println(delegateTask.getName());
        System.out.println(delegateTask.getId());
        System.out.println(delegateTask.getCategory());
        System.out.println(delegateTask.getEventName());
        System.out.println(delegateTask.getOwner());

    }
}
