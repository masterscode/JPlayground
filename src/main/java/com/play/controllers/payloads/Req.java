package com.play.controllers.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Req {
    @NotBlank
    @Pattern(regexp = "[ !@#$%^&*()_+-=|;':\\\",.<>?/~`]", message = "Password does not match pattern")
    private String password;
}
