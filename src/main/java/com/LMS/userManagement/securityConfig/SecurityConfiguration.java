package com.LMS.userManagement.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {


    private final   JwtAuthenticationFilter jwtAuthFilter;

    private final   AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       http.csrf(csrf ->csrf.disable())
                        .authorizeHttpRequests(auth->
                            auth.requestMatchers("lms/api/auth/**").permitAll()
                                    .requestMatchers("/lms/api/user/getAllCourse").permitAll()
                                    .requestMatchers("/lms/api/user/searchCourses").permitAll()
                                    .requestMatchers("/lms/api/user/getCourseById").permitAll()
                                    .requestMatchers("/lms/api/user/saveBadge").permitAll()
                                   .requestMatchers("/lms/api/auth/saveAndEditProfile").permitAll()
                                   /* .requestMatchers("/lms/api/user/").hasRole("user")
                                    .requestMatchers("/lms/api/admin").hasRole("admin")*/
                                    .anyRequest().authenticated()
                         )
                .sessionManagement(sess->sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
