package com.liulin.service;

import com.liulin.entity.McLeave;
import com.liulin.entity.McUser;
import com.liulin.mapper.McLeaveMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class McLeaveService {
    @Autowired
    McLeaveMapper mcLeaveMapper;
    /**
     * 通过请假表id-businessKey查询请假单
     */
    public McLeave findMcLeaveByBusinessKey(String businessKey){
        return mcLeaveMapper.findMcLeaveByBusinessKey(businessKey);
    }



    /**
     * 启动一个请假流程
     */
    public Integer addOneLeave(McLeave mcLeave) {
        return mcLeaveMapper.addLeave(mcLeave);
    }

    /**
     * 查询请假表中的数据
     *
     * @return
     */
    public List<McLeave> findMcLeavesByUser(Integer userId) {

        return mcLeaveMapper.findMcLeavesByUser(userId);

    }

    /**
     * 完成流程  0：未完成  1： 已完成
     * 流程结束之后通过businessKey更新mc_leave表
     */
    public Integer updateFinish(String businessKey) {
        System.out.println(businessKey);

        return mcLeaveMapper.updateFinish(businessKey);

    }


}
