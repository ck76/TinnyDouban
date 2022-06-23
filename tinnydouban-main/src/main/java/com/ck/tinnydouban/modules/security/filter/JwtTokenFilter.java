package com.ck.tinnydouban.modules.security.filter;


import com.ck.tinnydouban.exception.AccountLockedException;
import com.ck.tinnydouban.exception.TokenErrorException;
import com.ck.tinnydouban.modules.security.config.SecurityConfig;
import com.ck.tinnydouban.modules.security.service.SecurityDetailsService;
import com.ck.tinnydouban.modules.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("{\n" +
            "  \"alg\": \"HS256\",\n" +
            "  \"typ\": \"JWT\"\n" +
            "}")
    private String tokenHeader;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private SecurityDetailsService securityDetailsService;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 先从请求中拿到token
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;

        String url = httpRequest.getRequestURI();
        for (String u : SecurityConfig.ignoreUrl()) {
            if(url.contains(u)) {
                chain.doFilter(req, res);
                return;
            }
        }

        String token = httpRequest.getHeader(tokenHeader);
        if (!StringUtils.hasText(token)) {
            unsuccessfulAuthentication(httpRequest, httpResponse, new TokenErrorException());
            return;
        }

        try {

            // 从token中解析出手机号
            String phoneNumber = jwtTokenUtil.parsePhoneNumber(token);
            if (!StringUtils.isEmpty(phoneNumber) && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = securityDetailsService.loadUserByUsername(phoneNumber);

                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    // 生成通过认证
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    // 将权限写入本次会话
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                if (!userDetails.isEnabled()) {
                    unsuccessfulAuthentication(httpRequest, httpResponse, new AccountLockedException(null));
                    return;
                }
            }

        } catch (IOException e) {

            unsuccessfulAuthentication(httpRequest, httpResponse, new TokenErrorException(e));
            return;
        }


        chain.doFilter(req, res);
    }
}
