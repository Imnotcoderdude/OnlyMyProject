package com.sparta.onlymyproject.dtos.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RegisterRequest {

    @NotBlank(message = "사용자 이름에 공백은 허용되지 않습니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$", message = "영어와 숫자의 조합만 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호에 공백은 허용되지 않습니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "영어와 숫자의 조합만 가능합니다.")
    private String password;
    private String authority;
}
