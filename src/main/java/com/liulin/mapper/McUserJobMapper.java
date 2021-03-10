package com.liulin.mapper;

import com.liulin.entity.McUserJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface McUserJobMapper {

    /**
     * 统计所有的岗位
     *
     * @return
     */
    @Select("SELECT mc_user_job_name,tags ,details ,  COUNT(mc_user_job) as count_job   " +
            "FROM `mc_user` , mc_user_job " +
            "WHERE mc_user.mc_user_job = mc_user_job.mc_user_job_id  " +
            "GROUP BY mc_user_job")
    List<McUserJob> findAllJobs();





}
