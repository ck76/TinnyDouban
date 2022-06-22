package com.ck.tinnydouban.modules.security.config;

import com.ck.tinnydouban.modules.security.dto.Permission;
import com.ck.tinnydouban.modules.security.filter.JwtTokenFilter;
import com.ck.tinnydouban.modules.security.handler.SecurityInvalidTokenHandler;
import com.ck.tinnydouban.modules.security.handler.SecurityLockedHandler;
import com.ck.tinnydouban.modules.security.handler.SecurityPermissionDeniedHandler;
import com.ck.tinnydouban.modules.security.service.SecurityDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private SecurityDetailsService securityDetailsService;

    @Resource
    private SecurityPermissionDeniedHandler permissionDeniedHandler;

    @Resource
    private SecurityInvalidTokenHandler invalidTokenHandler;

    @Resource
    private SecurityLockedHandler lockedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源
        web.ignoring().antMatchers(HttpMethod.GET,
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/druid/**");
    }


    public static String[] ignoreUrl() {
        return new String[]{
                "user/register",
                "user/login",
                "user/password/**",
                "sms/**"
        };
    }

    /**
     * 请求配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(ignoreUrl())
                .permitAll() //无需任何认证和权限
                .antMatchers()
                .authenticated() // 之后的路由全部都需要token
                .antMatchers("/admin/**")
                .hasRole(Permission.ADMIN.getName())   // 需拥有 admin 这个权限
                .and()
                .exceptionHandling() // 开始设置异常处理
                .authenticationEntryPoint(invalidTokenHandler)// token无效时的处理逻辑
                .accessDeniedHandler(permissionDeniedHandler)// 权限不足时处理逻辑
                .and() // 禁用跨域和session
                .csrf().disable() // 禁用 Spring Security 自带的跨域处理
                .sessionManagement() // 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        /**
         * 本次 json web token 权限控制的核心配置部分
         * 在 Spring Security 开始判断本次会话是否有权限时的前一瞬间
         * 通过添加过滤器将 token 解析，将用户所有的权限写入本次会话
         */
        http.addFilterBefore(tokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 用户配置
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenFilter tokenFilterBean() throws Exception {
        JwtTokenFilter authenticationTokenFilter = new JwtTokenFilter();
        authenticationTokenFilter.setAuthenticationFailureHandler(lockedHandler);
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }


}
