package com.webshop.api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String username;
    @Size(min = 6, max = 25)
    private String password;
}
