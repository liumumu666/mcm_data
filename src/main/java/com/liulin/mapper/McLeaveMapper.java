package com.liulin.mapper;

import com.liulin.entity.McLeave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface McLeaveMapper {

    @Select("SELECT * FROM `mcm`.`mc_leave` WHERE mc_leave_user_id = #{mcLeave.mcLeaveUserId} and mc_leave_finish = #{mcLeave.mcLeaveFinish} LIMIT 0,1000")
    List<McLeave> findMcLeaveByFinish(@Param("mcLeave") McLeave mcLeave);

    List<McLeave> findAllMcLeave();






}
