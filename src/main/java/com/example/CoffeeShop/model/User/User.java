package com.example.CoffeeShop.model.User;

import com.example.CoffeeShop.service.ProdutoDTO.ProdutoRequestDTO;
import com.example.CoffeeShop.service.UserDTO.UserRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_Users")
@Entity(name = "T_Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_user")

public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_user;
  private String ds_login;
  private String ds_password;
  private String ds_role;

  public User(UserRequestDTO data) {
    this.ds_login = data.ds_login();
    this.ds_password = data.ds_password();
    this.ds_role = data.ds_role();
  }
}
