package com.example.fullsatckdevelopment.response;

import lombok.*;



@Builder
@AllArgsConstructor
@Setter
@Getter
public class GetStudentResponse {

    private Long id;
    private String fullName;

    private String email;

    private String password;

    private String department;
}
