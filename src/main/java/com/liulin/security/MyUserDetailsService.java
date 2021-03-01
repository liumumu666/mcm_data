package com.liulin.security;

import com.liulin.entity.McUser;
import com.liulin.service.McUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private McUserService mcUserService;

    @Override
    public UserDetails loadUserByUsername(String mcUserName) throws UsernameNotFoundException {
        System.out.println(mcUserName+"----------------------");
        McUser mcUser = mcUserService.findByName(mcUserName);
        if (mcUser == null) {
            return null;
        }
        log.info("user=={}", mcUser);
        List<GrantedAuthority> authority = new ArrayList<>();
        /**
         * 设置都能登录
         */
        authority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(
                mcUser.getMcUserName(),
                mcUser.getMcUserPassword(),
                authority
        );
    }
}
