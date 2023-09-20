package com.example.CoffeeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoffeeShop.Security.TokenService;
import com.example.CoffeeShop.model.User.User;
import com.example.CoffeeShop.repository.UserRepository;
import com.example.CoffeeShop.service.Exception.CustomException;
import com.example.CoffeeShop.service.UserDTO.AuthenticationDTO;
import com.example.CoffeeShop.service.UserDTO.LoginResponseDTO;
import com.example.CoffeeShop.service.UserDTO.RegisterDTO;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository repository;

  @Autowired
  private TokenService tokenService;

  // Register the client
  @PostMapping("/register")
  @CrossOrigin(origins = "http://localhost:3000")

  public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
    String errorMessage = "Não foi possível criar sua conta, tente novamente !";
    CustomException errorResponse = new CustomException(errorMessage);
    try {
      if (this.repository.findByLogin(data.login()) != null)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

      String encpryptedPassword = new BCryptPasswordEncoder().encode(data.ds_password());
      User newUser = new User(data.login(), encpryptedPassword, data.ds_role());

      this.repository.save(newUser);

      return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    catch (Exception e) {
      String errMsg = "Não foi Possível fazer login, tente novamente " + e.getMessage();
      CustomException errResp = new CustomException(errMsg);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResp);
    }
  }

  // Login the Client
  @PostMapping("/login")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
    try {
      var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.ds_password());
      var auth = this.authenticationManager.authenticate(usernamePassword);
      var token = tokenService.generateToken((User) auth.getPrincipal());
      return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    catch (Exception e) {
      String errorMessage = "Internal server error: " + e.getMessage();
      CustomException errorResponse = new CustomException(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }

}