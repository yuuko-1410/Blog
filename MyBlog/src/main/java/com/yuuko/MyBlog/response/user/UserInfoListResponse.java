package com.yuuko.MyBlog.response.user;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoListResponse {
    //分页
    private int current;
    private int page;
    private int size;
    private List<UserInfoResponse> userinfo;

}
