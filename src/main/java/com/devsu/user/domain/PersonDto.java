package com.devsu.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Long personId;
    @NotNull(message = "identificationNumber can not be null")
    @NotBlank(message = "identificationNumber can not be empty")
    private String identificationNumber;
    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be empty")
    private String name;
    @NotNull(message = "gender can not be null")
    @NotBlank(message = "gender can not be empty")
    private String gender;
    @NotNull(message = "age can not be null")
    private Integer age;
    @NotNull(message = "address can not be null")
    @NotBlank(message = "address can not be empty")
    private String address;
    @NotNull(message = "phone can not be null")
    @NotBlank(message = "phone can not be empty")
    private String phone;
}
