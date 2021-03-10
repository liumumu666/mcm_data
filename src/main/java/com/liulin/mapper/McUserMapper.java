package com.liulin.mapper;

import com.liulin.entity.McUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface McUserMapper {
    /**
     * 查询所有用户 用来登录
     *
     * @return
     */
    @Select("SELECT * FROM `mcm`.`mc_user` LIMIT 0,1000")
    List<McUser> findAllUsers();
    /**
     * 查询所有用户，分类
     */
    @Select("SELECT mc_user_id,mc_user_niname,mc_user_name,mc_user_sex,mc_user_age,mc_user_email,mc_user_job_name FROM `mcm`.`mc_user` , mc_user_job  " +
            "WHERE mc_user.mc_user_job = mc_user_job.mc_user_job_id and mc_user_job = 4 LIMIT #{start} , #{end}")
    List<McUser> findAllUsersByJob(@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 通过用户id查询用户
     *
     * @param mcUserId
     * @return
     */
    @Select("SELECT * FROM `mcm`.`mc_user` WHERE mc_user_id = #{mcUserId} LIMIT 0,1000")
    McUser findUserById(@Param("mcUserId") Integer mcUserId);

    /**
     * 通过用户名字查询用户
     *
     * @param mcUserName
     * @return
     */
    @Select("SELECT * FROM `mcm`.`mc_user` WHERE mc_user_name = #{mcUserName}")
    McUser findByName(@Param("mcUserName") String mcUserName);

    /**
     * 添加用户
     * @param mcUser
     * @return
     */

    @Insert("INSERT INTO `mcm`.`mc_user`(`mc_user_niname`, `mc_user_name`, `mc_user_sex`, `mc_user_password`, `mc_user_age`,`mc_user_email`) " +
            "VALUES (#{mcUser.mcUserNiname}, #{mcUser.mcUserName},#{mcUser.mcUserSex}, #{mcUser.mcUserPassword}, #{mcUser.mcUserAge}, #{mcUser.mcUserEmail})")
    @Options(useGeneratedKeys = true, keyProperty = "mcUserId")
    Integer addUser(@Param("mcUser") McUser mcUser);


}
