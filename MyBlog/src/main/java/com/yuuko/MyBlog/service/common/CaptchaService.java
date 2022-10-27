package com.yuuko.MyBlog.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务
 *
 * @author yuuko
 * @date 2022/10/19
 */
@Service
public class CaptchaService {
    @Autowired
    MailService mailService;
    @Autowired
    StringRedisTemplate redisTemplate;
    public String getCode(String emil){
        String captcha = UUID.randomUUID().toString().substring(0, 4);
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        String msg = "【锟斤拷】验证码："+captcha+" (10分钟内有效)";
        try{
            mailService.sendSimpleMail(emil,"验证码",msg);
        }catch (Exception e){
            throw new RuntimeException("邮件发送失败");
        }

        redisTemplate.opsForValue().set(code,captcha,10*60, TimeUnit.SECONDS);
        return code;
    }
}
