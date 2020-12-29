package com.security.example.demo.infrastructure.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO: Bu method authantication için yapılan configurasyonları sağlar.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    //TODO: Bu method authorization için yapılan configurasyonları sağlar.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // buradaki admin url ile gelen herkes admin rolüne sahip olmak zorunda. Url Level security
                .anyRequest().hasAnyRole("USER")
                .anyRequest().authenticated()
                .and() // anyRequest() geriye kalan diğer istekler ise user rolüne sahip olmalıdır.
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .and().httpBasic()
                .and()
                .sessionManagement().maximumSessions(1); //permit one session in same time (We also need some changes in code :))
    }

    //TODO:Örnek basic auth configi yine her şeyi aynı sadece bu iki method farklı

//    private static final String BASIC_AUTH_URL_PREFIX = "/**";
//    private static final String NOOP = "{noop}";
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(BASIC_AUTH_URL_PREFIX) Bu prefix ile başlayan bütün urlleri basic auth a sokar
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("apikey")
//                .password(NOOP + "secretKey")
//                .roles("USER");
//    }
}
