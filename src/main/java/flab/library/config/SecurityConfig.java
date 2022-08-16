package flab.library.config;

import flab.library.config.basic.BasicTokenAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;

@Slf4j
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
//    private final BasicAuthenticationFilter basicAuthenticationFilter;
//    private final BasicTokenAccessDeniedHandler basicTokenAccessDeniedHandler;
    //	private final BasicTokenAuthenticationEntryPoint basicTokenAuthenticationEntryPoint;
//    private final CorsFilter corsFilter;

    public SecurityConfig(UserDetailsService userDetailsService
//                          BasicTokenAccessDeniedHandler basicTokenAccessDeniedHandler,
//		BasicTokenAuthenticationEntryPoint basicTokenAuthenticationEntryPoint,
//                          CorsFilter corsFilter
    ) {
        this.userDetailsService = userDetailsService;
//        this.basicTokenAccessDeniedHandler = basicTokenAccessDeniedHandler;
//		this.basicTokenAuthenticationEntryPoint = basicTokenAuthenticationEntryPoint;
//        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        // For test, no encoding
         return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // .authorizeRequests()
                // .antMatchers("/v1/sign-in").authenticated()
                // .anyRequest().permitAll()

                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
//			.authenticationEntryPoint(basicTokenAuthenticationEntryPoint)
//                .accessDeniedHandler(basicTokenAccessDeniedHandler)

                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint())
        ;
    }

    @Bean
    public BasicTokenAuthenticationEntryPoint authenticationEntryPoint() {
        return new BasicTokenAuthenticationEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher(){
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher(){
            @Override
            public void sessionCreated(HttpSessionEvent event) {
                super.sessionCreated(event);
                log.info("===> Session created");
            }

            @Override
            public void sessionDestroyed(HttpSessionEvent event) {
                super.sessionDestroyed(event);
                log.info("===> Session destroyed");
            }

            @Override
            public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
                super.sessionIdChanged(event, oldSessionId);
                log.info("===> Session id changed");
            }
        });
    }

}
