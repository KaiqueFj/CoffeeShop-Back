package com.example.CoffeeShop.model.User;

public enum UserRole {
  ADMIN("admin"),

  USER("user");

  private String ds_role;

  UserRole(String ds_role) {
    this.ds_role = ds_role;
  }

  public String getRole() {
    return ds_role;
  }

}
