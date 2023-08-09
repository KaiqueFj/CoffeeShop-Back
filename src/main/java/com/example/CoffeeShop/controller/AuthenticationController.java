package com.example.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.InfraSecurity.TokenService;
import com.example.CoffeeShop.model.User.User;
import com.example.CoffeeShop.repository.UserRepository;
import com.example.CoffeeShop.service.UserDTO.AuthenticationDTO;
import com.example.CoffeeShop.service.UserDTO.LoginResponseDTO;
import com.example.CoffeeShop.service.UserDTO.RegisterDTO;
import com.example.CoffeeShop.service.UserDTO.UserRequestDTO;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository repository;

  @Autowired
  private TokenService tokenService;

  // Post the Client
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {

    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.ds_password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));

  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
    if (this.repository.findByLogin(data.login()) != null)
      return ResponseEntity.badRequest().build();

    String encpryptedPassword = new BCryptPasswordEncoder().encode(data.ds_password());
    User newUser = new User(data.login(), encpryptedPassword, data.ds_role());

    this.repository.save(newUser);

    return ResponseEntity.ok().build();

  }
}