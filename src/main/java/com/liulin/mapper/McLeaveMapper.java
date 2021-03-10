package com.liulin.mapper;

import com.liulin.entity.McLeave;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface McLeaveMapper {

    @Select("SELECT mc_leave_id as 'key',mc_leave_name,mc_leave_day,mc_leave_reason,mc_leave_start_time,mc_user_niname,mc_leave_finish FROM `mcm`.`mc_leave`, mc_user  WHERE mc_leave_user_id = mc_user_id and mc_leave_id =#{businessKey}")
    McLeave findMcLeaveByBusinessKey(@Param("businessKey") String businessKey);

    @Select("SELECT * FROM `mcm`.`mc_leave` WHERE mc_leave_user_id = #{mcLeave.mcLeaveUserId} and mc_leave_finish = #{mcLeave.mcLeaveFinish} LIMIT 0,1000")
    List<McLeave> findMcLeaveByFinish(@Param("mcLeave") McLeave mcLeave);

    //通过用户查询请假列表
    @Select("SELECT mc_leave_id as 'key',mc_leave_name,mc_leave_day,mc_leave_reason,mc_leave_start_time,mc_user_niname,mc_leave_finish FROM mc_leave , mc_user WHERE mc_leave_user_id = mc_user_id and mc_leave_user_id = #{userId}")
    List<McLeave> findMcLeavesByUser(@Param("userId") Integer userId);

    //填写请假单
    @Insert("INSERT INTO mc_leave (mc_leave_user_id,mc_leave_name,mc_leave_day,mc_leave_reason,mc_leave_start_time,mc_leave_finish) " +
            "VALUES (#{mcLeave.mcLeaveUserId},#{mcLeave.mcLeaveName},#{mcLeave.mcLeaveDay},#{mcLeave.mcLeaveReason},#{mcLeave.mcLeaveStartTime},0)")
    //返回插入的ID
    @Options(useGeneratedKeys = true, keyProperty = "mcLeaveId",keyColumn="mc_leave_id")
    Integer addLeave(@Param("mcLeave") McLeave mcLeave);

    /**
     * 完成流程  0：未完成  1： 已完成
     * 流程结束之后通过businessKey更新mc_leave表
     */
    @Update("UPDATE `mcm`.`mc_leave` SET `mc_leave_finish` = 1 WHERE `mc_leave_id` = #{businessKey}")
    Integer updateFinish(@Param("businessKey") String businessKey);






}
