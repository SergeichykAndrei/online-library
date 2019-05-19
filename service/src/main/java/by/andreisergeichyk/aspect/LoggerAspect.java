package by.andreisergeichyk.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * by.andreisergeichyk.service.*Service.save(..))")
    public void saveLogging() {
    }

    @Pointcut("execution(public * by.andreisergeichyk.service.*Service.delete(..))")
    public void deleteLogging() {
    }

    @AfterReturning(value = "saveLogging() && target(service) ")
    public void afterSaveMethodInvoke(Object service) {
        LOGGER.info(service.getClass() + " saved object");
    }

    @AfterReturning(value = "deleteLogging() && target(service) ")
    public void afterDeleteMethodInvoke(Object service) {
        LOGGER.info(service.getClass() + " delete object");
    }
}
