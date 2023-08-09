package com.example.CoffeeShop.service.UserDTO;

import com.example.CoffeeShop.model.User.User;

public record UserResponseDTO(Integer id_user, String ds_login, String ds_password, String ds_role) {
  public UserResponseDTO(User user) {
    this(user.getId_user(), user.getDs_login(), user.getDs_password(), user.getDs_role());
  }
}
