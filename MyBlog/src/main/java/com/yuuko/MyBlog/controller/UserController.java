package com.yuuko.MyBlog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.yuuko.MyBlog.common.restful.R;
import com.yuuko.MyBlog.domain.BlogUser;
import com.yuuko.MyBlog.request.RegisterRequest;
import com.yuuko.MyBlog.request.user.UserInfoRequest;
import com.yuuko.MyBlog.request.user.UserRequest;
import com.yuuko.MyBlog.response.user.UserInfoListResponse;
import com.yuuko.MyBlog.response.user.UserInfoResponse;
import com.yuuko.MyBlog.service.BlogUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Api(value = "用户接口", tags = {"用户接口"})
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    BlogUserService blogUserService;
    /**
     * 登录
     *
     * @return {@link String}
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    R login(@RequestBody UserRequest user){
        log.info("登录参数{}",user);
        SaTokenInfo tokenInfo = blogUserService.login(user);
        return R.success(tokenInfo);
    }

    /**
     * 注销
     *
     * @return {@link String}
     */
    @ApiOperation("退出登录")
    @PostMapping("/logout")
    R logout(){
        String msg = blogUserService.logout();
        return R.success(msg);
    }

    /**
     * 注册
     *
     * @return {@link String}
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    R register(@RequestBody RegisterRequest registerRequest){
        String msg = blogUserService.register(registerRequest);
        return R.success(msg);
    }

    /**
     * 设置用户信息
     *
     * @return {@link R}
     */
    @SaCheckLogin
    @ApiOperation("修改用户信息")
    @PostMapping("/setUserInfo")
    R setUserInfo(@RequestBody UserInfoRequest userInfoRequest){
        String msg = blogUserService.setUserInfo(userInfoRequest);
        return R.success(msg);
    }

    /**
     * 获取用户列表信息
     *
     * @param userInfoRequest 用户信息请求
     * @return {@link R}
     */
    @SaCheckLogin
    @ApiOperation("获取用户列表")
    @PostMapping("/getUserListByInfo")
    R getUserListByInfo(@RequestBody UserInfoRequest userInfoRequest){
        UserInfoListResponse userinfo = blogUserService.getUserListByInfo(userInfoRequest);
        return R.success(userinfo);
    }


    /**
     * 根据id获取用户信息
     *
     * @param id id
     * @return {@link R}
     */
    @SaCheckLogin
    @ApiOperation("获取用户信息")
    @GetMapping("/getUserById")
    R getUserById(@Param("id") String id){
        UserInfoResponse userInfoResponse = blogUserService.getUserById(id);
        return R.success(userInfoResponse);
    }


    /**
     * 验证码
     *s
     * @param emil 埃米尔
     * @return {@link R}
     */
    @ApiOperation("获取验证码")
    @GetMapping("/captcha")
    R getCaptcha(@Param("emil")String emil){
        String msg = blogUserService.getCaptcha(emil);
        return R.success(msg);
    }

    @PostMapping("/inset")
    R insert(@RequestBody BlogUser blogUser){
        String insert = blogUserService.insert(blogUser);
        return R.success(insert);
    }

}
