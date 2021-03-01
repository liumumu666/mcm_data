package com.liulin.config;


import com.liulin.handler.MyAuthenctiationFailureHandler;
import com.liulin.handler.MyAuthenctiationSuccessHandler;
import com.liulin.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * SpringSecurity的配置类
 */
@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MyAuthenctiationSuccessHandler myAuthSuccessHandler;
    @Autowired
    private MyAuthenctiationFailureHandler myAuthFailureHandler;

    /**
     * 用户授权
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义的认证类实现授权
        auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //其他任何路径都需要管理员登录
        http.authorizeRequests().
                antMatchers("/**").
                access("hasRole('ADMIN')");
        //登录相关配置
        http.cors();
        http.formLogin()
                .loginProcessingUrl("/login")       //指定处理登录的请求地址
                .successHandler(myAuthSuccessHandler) //登录成功的回调
                .failureHandler(myAuthFailureHandler); //登录失败的回调

        //登出配置
        http.logout().
                logoutUrl("/logout").           //登出地址为/logout
                invalidateHttpSession(true);    //并且登出后销毁session

        //关闭CSRF安全策略
        http.csrf().disable();

    }


}
