package com.yuuko.MyBlog.mapper;

import com.yuuko.MyBlog.domain.BlogUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author shibi
* @description 针对表【blog_user】的数据库操作Mapper
* @createDate 2022-10-18 16:41:19
* @Entity generator.domain.BlogUser
*/
@Mapper
public interface BlogUserMapper extends BaseMapper<BlogUser> {

}




