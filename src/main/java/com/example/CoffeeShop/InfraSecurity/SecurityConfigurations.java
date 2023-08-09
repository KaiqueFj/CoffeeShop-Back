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
            //

            .requestMatchers(HttpMethod.POST, "/dealer/postFornecedor").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/dealer/getAllFornecedor").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/dealer/getdealerById/{id_fornecedor}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/dealer/deleteDealer/{id_fornecedor}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/dealer/updateFornecedor/{id_fornecedor}").hasRole("ADMIN")
            //

            .requestMatchers(HttpMethod.POST, "/produto/addProduct/product").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/produto/getAllProducts").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/produto/getProdutoById/{id_produto}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/produto/deleteProduct/{id_produto}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/produto/updateProduct/{id_produto}").hasRole("ADMIN")
            //

            .requestMatchers(HttpMethod.POST, "/stock/addstock").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/stock/getAllStock").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/stock/getStock/{id_estoque}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/stock/deleteStock/{id_estoque}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/produto/updateStock/{id_estoque}").hasRole("ADMIN")
            //

            .requestMatchers(HttpMethod.POST, "/team/postTeam").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/team/getAllTeams").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/team/getTeamById/{id_equipe}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/team/deleteTeam/{id_equipe}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/team/updateTeamById/{id_equipe}").hasRole("ADMIN")
            //

            .requestMatchers(HttpMethod.POST, "/pedido/addPedido/Pedido").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/pedido/getAllOrders").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/pedido/getOrderById/{id_pedido}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/pedido/deleteOrder/{id_pedido}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/pedido/updateOrderById/{id_pedido}").hasRole("ADMIN")
            //

            .requestMatchers(HttpMethod.POST, "/invoice/addNotaFiscal/invoice").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/invoice/getAllInvoices").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/invoice/getInvoiceById/{id_pedido}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/invoice/deleteInvoice/{id_pedido}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/invoice/updateInvoice/{id_pedido}").hasRole("ADMIN")
            //

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
