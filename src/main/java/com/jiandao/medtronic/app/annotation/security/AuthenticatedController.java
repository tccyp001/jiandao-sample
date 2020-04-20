package com.jiandao.medtronic.app.annotation.security;

import com.jiandao.medtronic.app.config.SecurityConfig;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
@CrossOrigin(
        maxAge = SecurityConfig.MAX_AGE_SECONDS,
        methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT,
                RequestMethod.PATCH, RequestMethod.OPTIONS},
        origins = {"*"}
)
@RequestMapping
public @interface AuthenticatedController {
  @AliasFor(annotation = RequestMapping.class)
  String[] value () default {};
}
