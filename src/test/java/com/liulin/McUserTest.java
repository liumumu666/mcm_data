package com.liulin;

import com.liulin.entity.McUser;
import com.liulin.service.McUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class McUserTest {
    @Autowired
    McUserService mcUserService;
    @Test
    public void addUser(){

        McUser mcUser = new McUser();
        mcUser.setMcUserNiname("张经理");
        mcUser.setMcUserName("zhangjingli");
        mcUser.setMcUserPassword(new BCryptPasswordEncoder().encode("123123"));
        mcUser.setMcUserAge(18);
        mcUser.setMcUserEmail("799209027@qq.com");
        System.out.println(mcUserService.addUser(mcUser));

    }
    @Test
    public void testPassword(){
        System.out.println(new BCryptPasswordEncoder().encode("$2a$10$HX4CXSQuP.IwyNpBkM.nue8ZqP4XN8JWK.AOLVuffZvlrk0YFWXs2"));
    }

    @Test
    public void findByName(){
        System.out.println(mcUserService.findAllUsers());
    }



}
