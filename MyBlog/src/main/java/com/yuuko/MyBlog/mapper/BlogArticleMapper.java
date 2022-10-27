package com.yuuko.MyBlog.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.yuuko.MyBlog.domain.BlogArticle;
import org.apache.ibatis.annotations.Mapper;

/**
* @author shibi
* @description 针对表【blog_article】的数据库操作Mapper
* @createDate 2022-10-23 11:32:51
* @Entity generate.domain.BlogArticle
*/
@Mapper
public interface BlogArticleMapper extends MPJBaseMapper<BlogArticle> {

}




