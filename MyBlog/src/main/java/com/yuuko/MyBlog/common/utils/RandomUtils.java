package com.yuuko.MyBlog.common.utils;

public class RandomUtils {
    private final static String imgUrl = "https://fastly.jsdelivr.net/npm/typecho-joe-next@6.0.0/assets/thumb/";
    public static String getRandomImageUrl(){
        int nums = getRandomNum(1,42);
        return imgUrl+nums+".jpg";
    }

    public static int getRandomNum(int start,int end){
        return  (int) (Math.random() * (end + 1 -start) + start);
    }
}
