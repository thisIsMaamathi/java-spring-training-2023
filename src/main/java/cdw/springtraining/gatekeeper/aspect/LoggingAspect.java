package cdw.springtraining.gatekeeper.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Logging aspect is the aspect class that allows to log information about executing method.
 * It makes use of AOP to intercept method calls.
 *
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution( * cdw.springtraining.gatekeeper.controller.*.*(..))")
    private void forController() {
    }

    @Pointcut("execution( * cdw.springtraining.gatekeeper.service.*.*(..))")
    private void forService() {
    }

    @Pointcut("execution( * cdw.springtraining.gatekeeper.repository.*.*(..))")
    private void forRepository() {
    }

    @Pointcut("forController() || forService() || forRepository()")
    private void forAppFlow() {
    }


    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("@Before calling......." + method);

        Object[] arg = joinPoint.getArgs();
        logger.info("Arguments : ");
        for (Object temp : arg) {
            logger.info("====>  " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("The method " + method + " returns :===> " + result);
    }


}