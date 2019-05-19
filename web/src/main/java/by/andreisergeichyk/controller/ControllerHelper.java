package by.andreisergeichyk.controller;

import org.apache.log4j.Logger;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ControllerHelper {

    private static final String DEFAULT_ERROR_VIEW = "error";

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @ExceptionHandler(value = {ObjectOptimisticLockingFailureException.class})
    public String optimisticLockException(ObjectOptimisticLockingFailureException e){
        LOGGER.info(LocalDateTime.now(ZoneId.systemDefault()));
        LOGGER.info(e.getMessage());

        return "error-optimistic-lock";
    }

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        LOGGER.info(LocalDateTime.now(ZoneId.systemDefault()));
        LOGGER.info(request.getRequestURL());
        LOGGER.info(e.getMessage());

        return mav;
    }
}
