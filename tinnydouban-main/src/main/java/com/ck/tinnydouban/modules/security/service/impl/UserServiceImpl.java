package com.ck.tinnydouban.modules.security.service.impl;

import com.ck.tinnydouban.dao.UserMapper;
import com.ck.tinnydouban.exception.ApiException;
import com.ck.tinnydouban.modules.security.dto.JwtUser;
import com.ck.tinnydouban.modules.security.dto.Permission;
import com.ck.tinnydouban.modules.security.dto.vo.*;
import com.ck.tinnydouban.modules.security.service.UserRoleService;
import com.ck.tinnydouban.modules.security.service.UserService;
import com.ck.tinnydouban.modules.security.util.JwtTokenUtil;
import com.ck.tinnydouban.modules.security.util.RedisUtil;
import com.ck.tinnydouban.pojo.entity.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;



@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;


    @Resource
    private RedisUtil redisUtil;


    @Override
    public String register(RegisterVO registerVO) throws ApiException {

        ApiException.when(userMapper.selectByPhone(registerVO.getPhone()) != null, "手机号已被注册");
        checkCaptcha(registerVO.getPhone(), registerVO.getCaptcha());

        User user = new User();
        user.setPhone(registerVO.getPhone());
        user.setStatus(true);
        user.setCreatedTime(new Date(System.currentTimeMillis()));
        user.setUpdatedTime(new Date(System.currentTimeMillis()));
        // 密码不能存储明文，需要编码
        String encodePassword = passwordEncoder.encode(registerVO.getPassword());
        user.setPassword(encodePassword);

        ApiException.when(userMapper.insert(user) != 1, "注册失败");

        // 默认均为USER用户
        userRoleService.insert(user.getId(), Permission.USER.getId());

        // 生成token
        return jwtTokenUtil.generateToken(user.getPhone());
    }


    @Override
    public String loginByPhone(LoginVO loginVO) throws ApiException {

        User user = userMapper.selectByPhone(loginVO.getPhone());
        ApiException.when(user == null, "当前手机号未注册");
        ApiException.when(!passwordEncoder.matches(loginVO.getPassword(), user.getPassword()), "密码错误");
        return jwtTokenUtil.generateToken(user.getPhone());
    }

    @Override
    public String loginBySms(LoginSmsVO loginSmsVO) throws ApiException {

        User user = userMapper.selectByPhone(loginSmsVO.getPhone());
        ApiException.when(user == null, "手机号未注册");
        checkCaptcha(loginSmsVO.getPhone(), loginSmsVO.getCaptcha());
        return jwtTokenUtil.generateToken(user.getPhone());
    }

    @Override
    public String editPassword(EditPasswordVO passwordVO) throws ApiException {

        User user = userMapper.selectByPhone(passwordVO.getPhone());
        ApiException.when(user == null, "手机号未注册");
        ApiException.when(!passwordEncoder.matches(passwordVO.getPassword(), user.getPassword()), "密码错误");

        String newEncodePassword = passwordEncoder.encode(passwordVO.getNewPassword());
        user.setPassword(newEncodePassword);

        ApiException.when(userMapper.updateByPrimaryKey(user) != 1, "更新失败");
        return jwtTokenUtil.generateToken(user.getPhone());
    }


    @Override
    public String forgetPassword(RegisterVO registerVO) throws ApiException {
        // 验证手机号
        User user = userMapper.selectByPhone(registerVO.getPhone());
        ApiException.when(user == null, "手机号未注册");

        checkCaptcha(registerVO.getPhone(), registerVO.getCaptcha());

        String newEncodePassword = passwordEncoder.encode(registerVO.getPassword());
        user.setPassword(newEncodePassword);
        ApiException.when(userMapper.updateByPrimaryKey(user) != 1, "更新失败");
        return jwtTokenUtil.generateToken(user.getPhone());
    }


    @Override
    public int deleteUserById(Integer id) throws ApiException {
        int arg = userMapper.deleteByPrimaryKey(id);
        ApiException.when(arg == 0, "删除失败");
        // TODO: 2020/12/30 这里是否应该用外键
        ApiException.when(userRoleService.deleteByUid(id) == 0, "相关角色信息删除失败");
        return arg;
    }

    @Override
    public int lockByUid(Integer id) throws ApiException {
        User user = userMapper.selectByPrimaryKey(id);
        ApiException.when(user == null, "当前用户不存在");
        user.setStatus(false);
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int unlockByUid(Integer id) throws ApiException {
        User user = userMapper.selectByPrimaryKey(id);
        ApiException.when(user == null, "当前用户不存在");
        user.setStatus(true);
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public UserVO getUserInfoByToken() {

        UserVO userVo = new UserVO();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            User user = userMapper.selectByPhone(details.getUsername());
            BeanUtils.copyProperties(user, userVo);
        }
        return userVo;
    }

    @Override
    public UserVO getUserInfoById(Integer id) {


        UserVO vo = new UserVO();
        User user = userMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(user, vo);
        return vo;
    }


    /**
     * 检查验证码是否正确
     *
     * @param phone   手机号
     * @param captcha 验证码
     * @throws ApiException
     */
    private void checkCaptcha(String phone, String captcha) throws ApiException {
        String captchaCache = redisUtil.getStrValue(phone);
        ApiException.when(StringUtils.isEmpty(captchaCache), "未发送验证码或已过期");
        if (captchaCache.equals(captcha)) {
            // 验证成功后，验证码失效
            redisUtil.delete(phone);
        } else {
            ApiException.with("验证码错误");
        }
    }


    @Override
    public Long currentUserId() throws ApiException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApiException.when(auth == null, "认证失败");

        if (auth.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) auth.getPrincipal();
            if (details instanceof JwtUser) {
                User user = ((JwtUser) details).getUser();
                if (user != null) {
                    return Long.valueOf(user.getId());
                }
            }
        }
        throw new ApiException("认证失败");
    }


}
