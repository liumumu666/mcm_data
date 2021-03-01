package com.liulin.service;

import com.liulin.entity.McUser;
import com.liulin.mapper.McUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class McUserService {

    @Autowired
    McUserMapper mcUserMapper;

    /**
     * 查询所有用户 用于登录
     *
     * @return
     */
    public List<McUser> findAllUsers() {
        System.out.println("McUserService.findAllUsers");
        return mcUserMapper.findAllUsers();
    }
    /**
     * 查询所有员工
     */
    public List<McUser> findAllUsersByJob(Integer start,Integer end){
        System.out.println("McUserService.findAllUsersByJob");
        return mcUserMapper.findAllUsersByJob(start,end);
    }

    /**
     * 通过用户id查询用户
     *
     * @param mcUserId
     * @return
     */
    public McUser findUserById(Integer mcUserId) {
        System.out.println("McUserService.findUserById");
        return mcUserMapper.findUserById(mcUserId);
    }
    /**
     * 通过用户名字查询用户
     *
     * @param mcUserName
     * @return
     */
    public McUser findByName(String mcUserName) {
        System.out.println("McUserService.findByName");
        return mcUserMapper.findByName(mcUserName);
    }
    /**
     * 注册一个用户
     *
     * @param mcUser
     * @return
     */
    public Integer addUser(McUser mcUser) {
        System.out.println("McUserService.addUser");
        return mcUserMapper.addUser(mcUser);

    }


}
