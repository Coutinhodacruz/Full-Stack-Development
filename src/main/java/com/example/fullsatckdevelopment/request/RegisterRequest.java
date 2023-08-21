package com.example.fullsatckdevelopment.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String department;
}
