package com.example.rabbit.demo.notice.aop.aopUtils;

import com.example.rabbit.demo.notice.aop.aopUtils.ParamCheck;
import com.example.rabbit.demo.notice.aop.diyException.ParamIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class WebLogAcpect {
    // 用于记录每个controller执行的耗时时间，毫秒级
    private ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.example.rabbit.demo.notice.aop.controller.*.*(..))")
    public void webLog() {}

    /**
     * @param joinPoint
     * @throws Throwable
     * @desc 前置通知：在连接点之前执行的通知
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        timeLocal.set(startTime);// 记录开始时间

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //获取请求参数信息 和 requestBody数据
        List<String> paramList = new ArrayList<>();
        Object[] objArr = joinPoint.getArgs();
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof String) {
                paramList.add(String.valueOf(objArr[i]));
            }
        }
        log.info("AOP获取参数内容为: " + String.join("###", paramList));
    }


    /**
     * 检查参数是否为空
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = ((MethodSignature) pjp.getSignature());
        //得到拦截的方法
        Method method = signature.getMethod();
        System.out.println("参数检验中，方法名"+method);
        //获取方法参数注解，返回二维数组是因为某些参数可能存在多个注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return pjp.proceed();
        }
        //获取方法参数名
        String[] paramNames = signature.getParameterNames();
        //获取参数值
        Object[] paranValues = pjp.getArgs();
        //获取方法参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        System.out.println("参数检验中，参数类型"+parameterTypes);
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                //如果该参数前面的注解是ParamCheck的实例，并且notNull()=true,则进行非空校验
                if (parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof ParamCheck && ((ParamCheck) parameterAnnotations[i][j]).notNull()) {
                    paramIsNull(paramNames[i], paranValues[i], parameterTypes[i] == null ? null : parameterTypes[i].getName());
                    break;
                }
            }
        }
        return pjp.proceed();
    }

    /**
     * 参数非空校验，如果参数为空，则抛出ParamIsNullException异常
     * @param paramName
     * @param value
     * @param parameterType
     */
    private void paramIsNull(String paramName, Object value, String parameterType) {
        if (value == null || "".equals(value.toString().trim())) {
            throw new ParamIsNullException(paramName, parameterType);
        }
    }
    /**
     * @param ret
     * @throws Throwable
     * @desc 处理完成请求，返回的信息
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("返回的内容: " + ret);
        log.info("耗时： {} 毫秒", (System.currentTimeMillis() - timeLocal.get().longValue()));
        timeLocal.remove();
    }


}
