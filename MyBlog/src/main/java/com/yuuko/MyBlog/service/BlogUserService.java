package com.yuuko.MyBlog.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.yuuko.MyBlog.domain.BlogUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuko.MyBlog.request.RegisterRequest;
import com.yuuko.MyBlog.request.user.UserInfoRequest;
import com.yuuko.MyBlog.request.user.UserRequest;
import com.yuuko.MyBlog.response.user.UserInfoListResponse;
import com.yuuko.MyBlog.response.user.UserInfoResponse;

/**
* @author shibi
* @description 针对表【blog_user】的数据库操作Service
* @createDate 2022-10-18 16:41:19
*/
public interface BlogUserService extends IService<BlogUser> {
    SaTokenInfo login(UserRequest user);
    String logout();
    String register(RegisterRequest registerRequest);
    String getRole(Object loginId);
    String getCaptcha(String emil);
    String setUserInfo(UserInfoRequest userInfoRequest);
    UserInfoResponse getUserById(String id);
    UserInfoListResponse getUserListByInfo(UserInfoRequest userInfoRequest);
    String insert(BlogUser userRequest);//测试
}
