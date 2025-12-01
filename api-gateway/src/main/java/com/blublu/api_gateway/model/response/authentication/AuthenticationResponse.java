package com.blublu.api_gateway.model.response.authentication;

import lombok.Data;

@Data
public class AuthenticationResponse {
  private final String jwtToken;
}
