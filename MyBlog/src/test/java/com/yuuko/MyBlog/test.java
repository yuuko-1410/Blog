package com.yuuko.MyBlog;

import com.yuuko.MyBlog.response.article.ArticleInfoResponse;
import com.yuuko.MyBlog.service.BlogArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class test {
    @Autowired
    static BlogArticleService blogArticleService;

    @Test
    public void test(){
        ArticleInfoResponse articleDetailsById = blogArticleService.getArticleDetailsById("1583498849504620546");
        System.out.println(articleDetailsById);
    }
}
