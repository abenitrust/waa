package com.waa.lab.domain.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RefreshTokenRequestDto {
    @NotNull
    @NotBlank
    private String refreshToken;
}
