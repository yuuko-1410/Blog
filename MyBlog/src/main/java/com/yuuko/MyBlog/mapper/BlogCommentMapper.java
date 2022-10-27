package com.yuuko.MyBlog.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.yuuko.MyBlog.domain.BlogComment;
import org.apache.ibatis.annotations.Mapper;

/**
* @author shibi
* @description 针对表【blog_comment】的数据库操作Mapper
* @createDate 2022-10-24 17:59:22
* @Entity generate.domain.BlogComment
*/
@Mapper
public interface BlogCommentMapper extends MPJBaseMapper<BlogComment> {

}




