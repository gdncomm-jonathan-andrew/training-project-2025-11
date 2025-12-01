package com.blublu.api_gateway.model.response.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
  private String username;
}
