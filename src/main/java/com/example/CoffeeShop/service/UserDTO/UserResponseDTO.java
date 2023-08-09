package com.example.CoffeeShop.service.UserDTO;

import com.example.CoffeeShop.model.User.User;
import com.example.CoffeeShop.model.User.UserRole;

public record UserResponseDTO(Integer id_user, String ds_login, String ds_password, UserRole ds_role) {
  public UserResponseDTO(User user) {
    this(user.getId_user(), user.getDs_login(), user.getDs_password(), user.getDs_role());
  }
}
