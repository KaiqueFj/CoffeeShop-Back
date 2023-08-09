package com.example.CoffeeShop.model.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_user;
  private String ds_login;
  private String ds_password;
  private UserRole ds_role;

  public User(UserRequestDTO data) {
    this.ds_login = data.ds_login();
    this.ds_password = data.ds_password();
    this.ds_role = data.ds_role();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.ds_role == UserRole.ADMIN)
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getPassword() {

    return ds_password;
  }

  @Override
  public String getUsername() {

    return ds_login;
  }

  @Override
  public boolean isAccountNonExpired() {

    return true;
  }

  @Override
  public boolean isAccountNonLocked() {

    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {

    return true;
  }

  @Override
  public boolean isEnabled() {
    throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
  }
}
