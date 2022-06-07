package com.example.rabbit.demo.notice.aop.diyException;

import com.example.rabbit.demo.notice.aop.diyException.NullPonitException;
import com.example.rabbit.demo.notice.aop.diyException.ParamIsNullException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理.
 * 一般情况下，方法都有异常处理机制，但不能排除有个别异常没有处理，导致返回到前台，因此在这里做一个异常拦截，统一处理那些未被处理过的异常
 *
 * @author Beauxie
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 参数为空异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, ParamIsNullException.class})
    public void requestMissingServletRequest(Exception ex) {
        System.err.println(
                "全局捕获异常-参数为空异常处理"
        );
    }

    /**
     * 特别说明： 可以配置指定的异常处理,这里处理所有
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class, NullPonitException.class})
    public void errorHandler(HttpServletRequest request, Exception e) {
        System.err.println(
                "全局捕获异常-,这里处理所有"
        );
    }
}
