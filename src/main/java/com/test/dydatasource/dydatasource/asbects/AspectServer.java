package com.test.dydatasource.dydatasource.asbects;

import com.test.dydatasource.dydatasource.base.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


/**
 * Created by catherine on 2018/7/28.
 */
@Component
@Aspect
public class AspectServer {

    Logger logger = Logger.getLogger(String.valueOf(Aspect.class));

    private ThreadLocal<Long> timePeroid = new ThreadLocal<>();

    @Pointcut(value = "within(com.test.dydatasource.dydatasource.server..*)")
    public void doMethod() {
        logger.info("切入点方法执行中");
    }

    @Before(value = "within(com.test.dydatasource.dydatasource.server..*))")
    public void doBeforeMethod(JoinPoint joinPoint) {
        logger.info("before  ......");
        logger.info("当前方法为：" + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        if(args.length>0){
            Object arg = args[0];
            DynamicDataSourceContextHolder.setDataSourceType((String)arg);
        }
        logger.info("当前时间" + System.currentTimeMillis());
        timePeroid.set(System.currentTimeMillis());
    }

    @After(value = "execution(public * *.server.*Service.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("after .......");
        logger.info("当前方法为：" + joinPoint.getSignature().getName());
        logger.info("当前时间" + System.currentTimeMillis());
        logger.info("after方法执行...");
    }



    @AfterReturning(value = "doMethod()")
    public void doAfterMethod(JoinPoint joinPoint) {
        logger.info("afterreturning ....");
        logger.info("当前方发" + joinPoint.getSignature().getName());
        logger.info("耗时" + (System.currentTimeMillis() - timePeroid.get()));
        logger.info("当前时间" + System.currentTimeMillis());
    }

}
