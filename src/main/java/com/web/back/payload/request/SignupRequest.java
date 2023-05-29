package com.web.back.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

  @NotBlank
  @Size(max = 50)
  private String name;

  @NotBlank
  @Size(min = 10, max = 20)
  private String identification;

  @NotBlank
  @Size(min = 10, max = 20)
  private String telefono;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> roles;

  @NotBlank
  @Size(min = 8, max = 40)
  private String password;

}
