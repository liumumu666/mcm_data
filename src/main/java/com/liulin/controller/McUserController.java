package com.liulin.controller;

import com.liulin.entity.McUser;
import com.liulin.service.McUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class McUserController {

    @Autowired
    McUserService mcUserService;

    /**
     * 查询用户列表
     */
    @GetMapping("/user/findAllUsers")
    public List<McUser> findALlUsers() {
        return mcUserService.findAllUsers();
    }

    /**
     * 查询所有员工
     */
    @GetMapping("/user/findAllUsersByJob")
    public List<McUser> findAllUsersByJob(Integer start, Integer end) {
        return mcUserService.findAllUsersByJob(start, end);
    }

    /**
     * 通过用户名查询用户
     */

    @GetMapping("/user/checkUserName")
    public Boolean checkUserName(String userName) {
        if (mcUserService.findByName(userName) == null)
            return true;
        else
            return false;
    }

    /**
     * 通过id查询用户
     *
     * @param mcUserId
     * @return
     */
    @GetMapping("/user/findById/{mcUserId}")
    public McUser findUserById(@PathVariable(name = "mcUserId") Integer mcUserId) {
        return mcUserService.findUserById(mcUserId);
    }

    /**
     * 添加用户
     */
    @PostMapping("/user/addUser")
    public Integer addUser(@RequestBody McUser mcUser) {
        return mcUserService.addUser(mcUser);
    }


}
