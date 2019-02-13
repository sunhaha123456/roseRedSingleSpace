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
import java.util.Date;
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

    @Pointcut("execution (* com.rose.controler..*.*(..))")
    private void aspectMethod() {

    }

    @Pointcut("execution (* com.rose.controler..list*(..))")
    private void listMethod() {

    }

    @Pointcut("execution (* com.rose.controler..query*(..))")
    private void queryMethod() {

    }

    @Pointcut("execution (* com.rose.controler..get*(..))")
    private void getMethod() {

    }

    @Pointcut("execution (* com.rose.controler..count*(..))")
    private void countMethod() {

    }

    @Pointcut("execution (* com.rose.controler..check*(..))")
    private void checkMethod() {

    }

    @Pointcut("execution (* com.rose.controler..export*(..))")
    private void exportMethod() {

    }

    @Pointcut("execution (* com.rose.controler..to*(..))")
    private void toMethod() {

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
                    TbSysUserLog sysUserLog = new TbSysUserLog(userIdHolder, url, ip, JsonUtil.objectToJson(newList), returnValue + "");
                    sysUserLog.setCreateDate(new Date());
                    sysUserLogRepository.save(sysUserLog);
                }
        } catch (Throwable e) {
            log.error("Throwable 日志记录失败！", e);
        }
    }
}