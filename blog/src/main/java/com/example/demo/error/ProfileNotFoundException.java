package com.example.demo.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProfileNotFoundException extends RuntimeException {

  private final int personId;

  @Override
  public String getMessage() {
    return "Profile with id = " + personId + " exist";
  }
}
