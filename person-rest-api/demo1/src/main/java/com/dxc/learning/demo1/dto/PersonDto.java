package com.dxc.learning.demo1.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class PersonDto {

    @Id
    @NotBlank(message = "id is mandatory")
    private Integer id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "DOB cannot be null")
    private LocalDate dob;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Email cannot be empty")
    private String email;

}