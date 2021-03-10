package com.liulin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liulin.entity.McUser;
import com.liulin.entity.Result;
import com.liulin.service.McUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("myAuthSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private McUserService mcUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("###########登录成功");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("name=" + name);


        McUser mcUser = mcUserService.findByName(name);
        request.getSession().setAttribute("mcUser",mcUser);
        Result result = new Result(200, "登录成功！！");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(mcUser));
    }
}

