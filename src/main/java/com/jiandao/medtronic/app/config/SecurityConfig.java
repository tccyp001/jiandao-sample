package com.jiandao.medtronic.app.config;

import com.jiandao.medtronic.app.annotation.security.LocalController;
import com.jiandao.medtronic.app.annotation.security.PublicController;
import com.jiandao.medtronic.app.security.JwtAuthenticationService;
import com.jiandao.medtronic.app.util.AnnotationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public static final int MAX_AGE_SECONDS = 3600;

  private Environment env;

  private JwtAuthenticationService jwtAuthenticationService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors()
              .and()
            .csrf()
              .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    if ("dev".equals(env.getProperty("environment"))) {
      http.authorizeRequests().anyRequest().permitAll();
    } else {

      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
              configure = http.authorizeRequests();

      // Set up authentication by annotation
      // Public Controllers
      List<PublicController> publicControllers = AnnotationUtils.findAllAnnotations(PublicController.class);
      publicControllers.forEach(
              t -> configure
                      .antMatchers(t.value()[0] + "/**")
                      .permitAll()
      );

      // Local Controllers
      List<LocalController> localControllers = AnnotationUtils.findAllAnnotations(LocalController.class);
      localControllers.forEach(
              t -> configure
                      .antMatchers(t.value()[0] + "/**")
                      .access("hasIpAddress('127.0.0.1') or hasIpAddress('0:0:0:0:0:0:0:1')")
      );

      configure.anyRequest().authenticated();
    }

    http.addFilterBefore(jwtAuthenticationService, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void setEnv(Environment env){
    this.env = env;
  }

  @Autowired
  public void setJwtAuthenticationService(JwtAuthenticationService jwtAuthenticationService){
    this.jwtAuthenticationService = jwtAuthenticationService;
  }
}
