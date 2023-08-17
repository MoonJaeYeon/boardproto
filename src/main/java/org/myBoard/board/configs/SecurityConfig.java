package org.myBoard.board.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myBoard.board.models.member.LoginFailureHandler;
import org.myBoard.board.models.member.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .successHandler(new LoginSuccessHandler()) // 로그인 성공시 페이지
                .failureHandler(new LoginFailureHandler()) // 실패시 로그인 페이지
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/login");

        http.authorizeHttpRequests()
                .requestMatchers("/mypage/**").authenticated() // 회원 전용 url /** mypage포함 상위클래스
                // .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자 전용
                .anyRequest().permitAll(); // 그 외 모든 페이지는 모든 회원이 접근 가능

        http.exceptionHandling()
                        .authenticationEntryPoint((req, res, e)->{
                            String URI = req.getRequestURI();

                            if (URI.indexOf("/admin") != -1) { // 관리자 페이지
                                 res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED"); // 권한 없음
                            }else { // 회원 페이지
                                String redirctURL = req.getContextPath() + "/member/login";
                                res.sendRedirect(redirctURL);
                            }


                        });

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**"); // 무력화
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
