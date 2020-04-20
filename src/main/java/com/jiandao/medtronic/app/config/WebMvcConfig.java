package com.jiandao.medtronic.app.config;


import com.jiandao.medtronic.app.annotation.security.AuthenticatedController;
import com.jiandao.medtronic.app.annotation.security.LocalController;
import com.jiandao.medtronic.app.annotation.security.PublicController;
import com.jiandao.medtronic.app.util.ErrorCode;
import com.jiandao.medtronic.app.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @ControllerAdvice(annotations = {PublicController.class, LocalController.class, AuthenticatedController.class})
    public class UncaughtExceptionsControllerAdvice {
        @ExceptionHandler({MethodArgumentNotValidException.class})
        public ResponseEntity handleBindingErrors(Exception ex) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            ErrorCode errorCode = ErrorCode.HTTP_ERROR;
            String field = "";
            try {
                String[] brokenUp = e.getBindingResult().getFieldError().getField().split("\\.");
                System.out.println(Arrays.toString(brokenUp));
                field = brokenUp[brokenUp.length - 1]
                        // Convert to normal case
                        .replaceAll(
                                String.format("%s|%s|%s",
                                        "(?<=[A-Z])(?=[A-Z][a-z])",
                                        "(?<=[^A-Z])(?=[A-Z])",
                                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                                ),
                                " "
                        ).toLowerCase();
            } catch (Exception ignored){

            }

            return new ResponseEntity<>(ResponseUtil.getResponseByErrorCode(errorCode, field), HttpStatus.OK);
        }
    }


}
