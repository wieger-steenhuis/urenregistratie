package com.sx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by udr013 on 12-5-2016.
 */

//this is all spring security: WebSecurityConfigurerAdapter, configureGlobal, AuthenticationManagerBuilder. That's just how it is
@Configuration
@EnableWebSecurity //this secures the web environment

//extending this class just gives us a lot of reasonable default settings
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DriverManagerDataSource driverManagerDataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index","/Stylesheet.css","/images/**","/loginTo").permitAll()
                .antMatchers("/login2","/trainer_home").hasAuthority("TRAINER")
                .antMatchers("/admin_home").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/")//because we override and create our own login page we need to give permission
                // to access this login page by adding the permitAll()as good practise so unauthenticated users can access it for sure!!
                //.failureUrl("/")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/loginTo")
                .and()
                .logout().permitAll()
               .and()
                .exceptionHandling().accessDeniedPage("/unauthorized");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        System.out.println("we are here");
        authenticationManagerBuilder.jdbcAuthentication().dataSource(driverManagerDataSource)

       .usersByUsernameQuery("select username, password, enabled from trainer where username=?")
        .authoritiesByUsernameQuery("select username, role from user_roles where username=?")
        ;
//        System.out.println("we are here now");
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("TRAINER").and().withUser("admin").password("password").roles("ADMIN")
//        ;

    }
}