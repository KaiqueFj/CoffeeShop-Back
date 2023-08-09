package com.example.CoffeeShop.InfraSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

  @Autowired
  SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
            //
            .requestMatchers(HttpMethod.POST, "/funcionario/addFuncionario").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/funcionario/getAllFuncionarios").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/funcionario/getEmployeebyId/{id_funcionario}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/funcionario/deleteFuncionarioByid/{id_funcionario}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/funcionario/updateFuncionario/{id_funcionario}").hasRole("ADMIN")

            .requestMatchers(HttpMethod.POST, "/cliente/addUser/client").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/cliente/getAllClients").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/cliente/getClient/{id_cliente}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/cliente/deleteClient/{id_cliente}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/cliente/updateClient/{id_cliente}").hasRole("ADMIN")

            .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
