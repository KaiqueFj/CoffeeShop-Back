package com.example.CoffeeShop.service.UserDTO;

import com.example.CoffeeShop.model.User.UserRole;

public record UserRequestDTO(String ds_login, String ds_password, UserRole ds_role) {

}