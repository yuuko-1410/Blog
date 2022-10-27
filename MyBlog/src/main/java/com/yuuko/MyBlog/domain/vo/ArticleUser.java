package com.yuuko.MyBlog.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleUser {
    @TableId
    private String tid;
    private String uid;
    private String title;
    private String context;
    private Integer likeNum;
    private Integer watchNum;
    private Date createTime;
    private Date updateTime;
    private Integer disable;
    private String imgUrl;
    //联表
    private String username;

}
