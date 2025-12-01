package com.blublu.api_gateway.model.request.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
  private String username;
  private String password;
}
