package com.blublu.api_gateway.model.request.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
  private String username;
  private String password;
}
