package com.yuuko.MyBlog.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.yuuko.MyBlog.common.restful.ErrorCode;
import com.yuuko.MyBlog.common.restful.GlobalErrorCodeConstants;
import com.yuuko.MyBlog.common.restful.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未知异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception e) {
        // 把错误信息输入到日志中
        log.error(e.toString());
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R httpMessageNotReadableException(HttpMessageNotReadableException e) {
        // 把错误信息输入到日志中
        log.error(e.toString());
        return R.fail(GlobalErrorCodeConstants.BAD_REQUEST);
    }

    @ExceptionHandler(NotRoleException.class)
    @ResponseBody
    public R notRoleException(NotRoleException e) {
        // 把错误信息输入到日志中
        log.error(e.toString());
        return R.fail(GlobalErrorCodeConstants.NOT_ROLE);
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public R notLoginException(NotLoginException e) {
        // 把错误信息输入到日志中
        log.error(e.toString());
        return R.fail(GlobalErrorCodeConstants.NOTR_LOGIN);
    }

}
