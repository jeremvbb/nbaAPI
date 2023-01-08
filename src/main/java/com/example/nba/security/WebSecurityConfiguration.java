package com.example.nba.security;

import com.example.nba.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
/*
@EnableWebSecurity
@Configuration

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = false,
        prePostEnabled = true
)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

 private BCryptPasswordEncoder bCryptPasswordEncoder;
 private UserDetailsServiceImpl userDetailsService;

 private static final String[] AUTH_WHITELIST = {
         "/h2-console/**",
         "/api/**",
         "/swagger-ui/**",
         "/swagger-ui.html/**",
         "/player/**",
         "/auth/**",
         "/swagger-resources/**",
         "/configuration/ui",
         "/configuration/security",
         "/students/**",
         "/v2/**",
         "/v3/**",
         "/login/**",
         "/webjars/**"
 };



 public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  this.userDetailsService = userDetailsService;

 }


 protected void configure(HttpSecurity httpSecurity) throws Exception {
  httpSecurity.cors().and().csrf().disable().authorizeRequests()
          .antMatchers(AUTH_WHITELIST).permitAll()
          .antMatchers(HttpMethod.POST, "/signup").permitAll()
          .antMatchers(HttpMethod.POST, "/login").permitAll()
          .antMatchers(HttpMethod.POST, "/auth").permitAll()
          .antMatchers(HttpMethod.GET, "/login").permitAll()
          .antMatchers(HttpMethod.GET, "/signup").permitAll()
          .anyRequest().authenticated()
          .and().addFilter(new AuthenticationFilter(authenticationManager()))
          .addFilter(new AuthorizationFilter(authenticationManager()))
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  //this will allow frames with same origin which is much more safe
  httpSecurity.headers().frameOptions().sameOrigin();
 }

 public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
  authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
 }
 @Override
 @Bean
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }


 @Bean
 CorsConfigurationSource corsConfigurationSource() {
  final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
  return source;
 }




}*/

import com.example.nba.security.JwtAuthenticationEntryPoint;
import com.example.nba.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(

        prePostEnabled = true, proxyTargetClass = true
)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
 @Autowired
 UserDetailsServiceImpl customUserDetailsService;

 @Autowired
 private JwtAuthenticationEntryPoint unauthorizedHandler;

 @Bean
 public JwtAuthenticationFilter jwtAuthenticationFilter() {
  return new JwtAuthenticationFilter();
 }

 @Override
 public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
  authenticationManagerBuilder
          .userDetailsService(customUserDetailsService)
          .passwordEncoder(passwordEncoder());


 }
 private static final String[] AUTH_WHITELIST = {
         "/h2-console/**",
         "/api/**",
         "/swagger-ui/**",
         "/swagger-ui.html/**",
         "/player/**",
         "/statistic/**",
         "/team/**",
         "/game/**",
         "/standing/**",
         "/auth/**",
         "/swagger-resources/**",
         "/configuration/ui",
         "/configuration/security",
         "/students/**",
         "/v2/**",
         "/v3/**",
         "/login/**",
         "/webjars/**"
 };


 @Bean(BeanIds.AUTHENTICATION_MANAGER)
 @Override
 public AuthenticationManager authenticationManagerBean() throws Exception {
  return super.authenticationManagerBean();
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http
          .cors()
          .and()
          .csrf()

          .disable()
          .exceptionHandling()
          .authenticationEntryPoint(unauthorizedHandler)
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authorizeRequests()
          .antMatchers(AUTH_WHITELIST).permitAll()
          .antMatchers(HttpMethod.POST, "/signup").permitAll()
          .antMatchers(HttpMethod.POST, "/login").permitAll()
          .antMatchers(HttpMethod.POST, "/auth").permitAll()
          .antMatchers(HttpMethod.GET, "/login").permitAll()
          .antMatchers(HttpMethod.GET, "/signup").permitAll()
          //.antMatchers("/player**").permitAll()

          .anyRequest().authenticated();
 /* http
          // ...
          .headers()
          .defaultsDisabled()
          .cacheControl();
 http.formLogin().and()   // (2)
          .httpBasic();*/
  // Add our custom JWT security filter
  http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

 }


}

