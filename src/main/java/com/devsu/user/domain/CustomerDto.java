package com.devsu.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto extends PersonDto {
    private Long customerId;
    @NotNull(message = "password can not be null")
    @NotBlank(message = "password can not be empty")
    private String password;
    private Boolean state = true;
}
