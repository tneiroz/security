package me.eun.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import me.eun.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@ComponentScan("computer.dad.security")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

   @Autowired
   AuthenticationSuccessHandler loginSuccessHandler;
   
   
   @Autowired
   CustomUserDetailService customUserDetailService;
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(customUserDetailService)
      .passwordEncoder(bcryptPwEncoder());
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      // 스프링 시큐리어티 적용 후 한글 설정
      CharacterEncodingFilter filter = new CharacterEncodingFilter(); 
      filter.setEncoding("utf-8");
      filter.setForceEncoding(true);
      http.addFilterBefore(filter, CsrfFilter.class);
      
      http.authorizeRequests()
         .antMatchers("/member/all").permitAll()
         .antMatchers("/member/admin").access("hasRole('ROLE_ADMIN')")
         .antMatchers("/member/member").access("hasRole('ROLE_MEMBER')");
      http.formLogin()
         .loginPage("/customLogin")
         .loginProcessingUrl("/login")
         .successHandler(loginSuccessHandler);
      http.logout()
         .logoutUrl("/member/logout")
         .logoutSuccessUrl("/")
         .invalidateHttpSession(true)
         .deleteCookies("remember-me", "JSESSION_ID");
      http.rememberMe().key("project")
         .tokenRepository(persistentTokenRepository())
         .tokenValiditySeconds(604800);
   }
   
      @Autowired
      DataSource dataSource;
      @Bean
      public PersistentTokenRepository persistentTokenRepository() {
         JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
         repo.setDataSource(dataSource);
         return repo; 
      }
      @Bean
      public  PasswordEncoder bcryptPwEncoder() {
         return new BCryptPasswordEncoder();
      }

}