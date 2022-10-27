package com.yuuko.MyBlog.service.impl;

import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuuko.MyBlog.domain.BlogUser;
import com.yuuko.MyBlog.request.RegisterRequest;
import com.yuuko.MyBlog.request.user.UserInfoRequest;
import com.yuuko.MyBlog.request.user.UserRequest;
import com.yuuko.MyBlog.response.user.UserInfoListResponse;
import com.yuuko.MyBlog.response.user.UserInfoResponse;
import com.yuuko.MyBlog.service.BlogUserService;
import com.yuuko.MyBlog.mapper.BlogUserMapper;
import com.yuuko.MyBlog.service.common.CaptchaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author shibi
* @description 针对表【blog_user】的数据库操作Service实现
* @createDate 2022-10-18 16:41:19
*/
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService{
    @Autowired
    BlogUserMapper blogUserMapper;
    @Autowired
    CaptchaService captchaService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public SaTokenInfo login(UserRequest user) {
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username",user.getUsername())
                .or().eq("emil",user.getUsername());//支持邮箱当用户名登录
        BlogUser blogUser = blogUserMapper.selectOne(queryWrapper);
        System.out.println(blogUser);
        if (blogUser == null){
            throw new RuntimeException("账号不存在");
        }
        if (!blogUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        StpUtil.login(blogUser.getUid());
        return StpUtil.getTokenInfo();
    }

    @Override
    public String logout() {
        StpUtil.logout();
        return "退出登陆成功";
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (registerRequest.getCode() == null){
            throw new RuntimeException("参数异常");
        }

        //判断邮箱是否被注册
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emil", registerRequest.getEmil());
        if (blogUserMapper.selectOne(queryWrapper) != null){
            throw new RuntimeException("该邮箱已被注册");
        }
        //验证码校验
        String code = redisTemplate.opsForValue().get(registerRequest.getCode());
        if (!(registerRequest.getCaptcha()).equals(code)){
            throw new RuntimeException("验证码错误");
        }

    //插入数据
    BlogUser blogUser = new BlogUser();
        blogUser.setUsername(registerRequest.getUsername());
        blogUser.setPassword(registerRequest.getPassword());
        blogUser.setUserAuthority("user");
        blogUser.setEmil(registerRequest.getEmil());
        blogUserMapper.insert(blogUser);
        return "账号注册成功";
}
    @Override
    public String getRole(Object loginId) {
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", loginId);
        BlogUser blogUser = blogUserMapper.selectOne(queryWrapper);
        return blogUser.getUserAuthority();
    }

    @Override
    public String getCaptcha(String emil) {
        return captchaService.getCode(emil);
    }

    @Override
    public String setUserInfo(UserInfoRequest userInfoRequest) {
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        //传入uid可修改指定用户信息，只有admin角色才能进行修改

        if (userInfoRequest.getUid() != null){
            if (!StpUtil.hasRole("admin")) {
                throw new NotRoleException("无权限");
            }
            queryWrapper.eq("uid",userInfoRequest.getUid());
        }else{
            queryWrapper.eq("uid",StpUtil.getLoginId());
        }
        BlogUser blogUser = blogUserMapper.selectOne(queryWrapper);
        //修改数据
        if (userInfoRequest.getDisable() != null){
            blogUser.setDisable(userInfoRequest.getDisable());
        }
        if (userInfoRequest.getUsername() != null){
            blogUser.setUsername(userInfoRequest.getUsername());
        }
        if (userInfoRequest.getPassword() != null){
            blogUser.setPassword(userInfoRequest.getPassword());
            //修改密码后踢下线
            StpUtil.logout(blogUser.getUid());
        }
        if (userInfoRequest.getUserAuthority() != null){
            blogUser.setUserAuthority(userInfoRequest.getUserAuthority());
        }
        if (userInfoRequest.getEmil() != null){
            blogUser.setEmil(userInfoRequest.getEmil());
        }
        if (userInfoRequest.getPhoneNumber() != null){
            blogUser.setPhoneNumber(userInfoRequest.getPhoneNumber());
        }
        //执行修改
        blogUserMapper.update(blogUser,queryWrapper);
        return "更新用户信息成功";
    }

    @Override
    public UserInfoResponse getUserById(String id) {
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",id);
        BlogUser blogUser = blogUserMapper.selectOne(queryWrapper);
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        BeanUtils.copyProperties(blogUser,userInfoResponse);
//        userInfoResponse.setEmil(blogUser.getEmil());
//        userInfoResponse.setUsername(blogUser.getUsername());
//        userInfoResponse.setPhoneNumber(blogUser.getPhoneNumber());
//        userInfoResponse.setUid(blogUser.getUid());
//        userInfoResponse.setAvatarUrl(blogUser.getAvatarUrl());
        return userInfoResponse;
    }

    @Override
    public UserInfoListResponse getUserListByInfo(UserInfoRequest userInfoRequest) {
        //获取查询的方式
        String operateType = userInfoRequest.getType();
        String pram = userInfoRequest.getPram();

        Page<BlogUser> page = new Page<>(userInfoRequest.getCurrent(), userInfoRequest.getPage());
        QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();

        //搜索功能
        if (!"".equals(pram)){
            queryWrapper.like("username","%"+pram+"%")
                    .or().like("phone_number","%"+pram+"%")
                    .or().like("emil","%"+pram+"%");
        }

        switch (operateType){
            case "state_ban"://封禁中
                queryWrapper.eq("disable",2);
                break;
            case "state_none"://已注销
                queryWrapper.eq("disable",3);
                break;
            default://可用
                queryWrapper.eq("disable",1);
                break;
        }

        //筛选经过修改的参数
        if (userInfoRequest.getUsername() != null){
            queryWrapper.eq("username",userInfoRequest.getUsername());
        }
        if (userInfoRequest.getUserAuthority() != null){
            queryWrapper.eq("user_authority",userInfoRequest.getUserAuthority());
        }
        if (userInfoRequest.getEmil() != null){
            queryWrapper.eq("emil",userInfoRequest.getEmil());
        }
        if (userInfoRequest.getPhoneNumber() != null){
            queryWrapper.eq("phone_number",userInfoRequest.getPhoneNumber());
        }
        if (userInfoRequest.getDisable() != null){
            queryWrapper.eq("disable",userInfoRequest.getDisable());
        }
//        queryWrapper.or().eq("uid","");
        //分页查询
        Page<BlogUser> blogUsersPage = blogUserMapper.selectPage(page, queryWrapper);
        List<BlogUser> blogUsers = blogUsersPage.getRecords();
        UserInfoListResponse userInfoListResponse = new UserInfoListResponse();
        List<UserInfoResponse> userInfoResponsesList = new ArrayList<>();
        for (BlogUser user : blogUsers) {
            UserInfoResponse temp = new UserInfoResponse();
            BeanUtils.copyProperties(user,temp);

//            temp.setUid(user.getUid());
//            temp.setUsername(user.getUsername());
//            temp.setAvatarUrl(user.getAvatarUrl());
//            temp.setPhoneNumber(user.getPhoneNumber());
//            temp.setEmil(user.getEmil());
            userInfoResponsesList.add(temp);
        }
        userInfoListResponse.setUserinfo(userInfoResponsesList);
        userInfoListResponse.setCurrent(userInfoRequest.getCurrent());
        userInfoListResponse.setPage(userInfoRequest.getPage());
        userInfoListResponse.setSize((int) page.getTotal());
        return userInfoListResponse;
    }

    @Override
    public String insert(BlogUser userRequest) {
        for (int i = 0; i < 10; i++) {
            blogUserMapper.insert(userRequest);
        }
        return "插入成功";
    }
}




