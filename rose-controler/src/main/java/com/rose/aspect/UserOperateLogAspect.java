package com.rose.aspect;

import com.rose.common.util.IpUtil;
import com.rose.common.util.JsonUtil;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbSysUserLog;
import com.rose.repository.SysUserLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能：用户操作行为的日志记录切面
 * Created by jay on 16-5-18.
 */
@Aspect
@Configuration
@Slf4j
public class UserOperateLogAspect {

    @Inject
    private SysUserLogRepository sysUserLogRepository;

    @Inject
    private ValueHolder valueHolder;

    @Pointcut("execution (* com.szhl.settle.web.controller..*.*(..))")
    private void aspectMethod() {

    }

    @Pointcut("execution (* com.szhl.settle.web.controller..list*(..))")
    private void listMethod() {

    }

    @Pointcut("execution (* com.szhl.settle.web.controller..query*(..))")
    private void queryMethod() {

    }

    @Pointcut("execution (* com.szhl.settle.web.controller..get*(..))")
    private void getMethod() {

    }

    @Pointcut("execution (* com.szhl.settle.web.controller..count*(..))")
    private void countMethod() {

    }

    @Pointcut("execution (* com.szhl.settle.web.controller..check*(..))")
    private void checkMethod() {

    }

    @Pointcut("execution (* com.szhl.settle.web.controller..export*(..))")
    private void exportMethod() {

    }

    @AfterReturning(value = "aspectMethod() && !listMethod() && !queryMethod() && !getMethod() && !countMethod() && !checkMethod() && !exportMethod()", returning = "returnValue")
    public void after(JoinPoint point, Object returnValue) {
        try {
                Object[] args = point.getArgs();
                List<Object> objects = Arrays.asList(args);
                List<Object> newList = new ArrayList<>();
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                String url = request.getRequestURI();
                String ip = IpUtil.getIp(request);
                if (!url.contains("import")) {
                    for (int i = 0; i < objects.size(); i++) {
                        if (objects.get(i) instanceof HttpServletRequest || objects.get(i) instanceof HttpServletResponse || objects.get(i) instanceof BindingResult) {
                            continue;
                        }
                        newList.add(objects.get(i));
                    }
                }
                //记录操作日志
                Long userIdHolder = valueHolder.getUserIdHolder();
                if (userIdHolder != null) {
                    TbSysUserLog sysUserLog = new TbSysUserLog(Long.valueOf(userIdHolder), url, ip, JsonUtil.objectToJson(newList), returnValue + "");
                    sysUserLogRepository.save(sysUserLog);
                }
        } catch (Throwable e) {
            log.error("Throwable 日志记录失败！", e);
        }
    }
}