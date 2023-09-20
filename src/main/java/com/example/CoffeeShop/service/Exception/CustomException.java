package com.example.CoffeeShop.service.Exception;

public class CustomException {
  private String message;

  public CustomException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
