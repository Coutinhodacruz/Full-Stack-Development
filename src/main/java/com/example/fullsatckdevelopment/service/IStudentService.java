package com.example.fullsatckdevelopment.service;

import com.example.fullsatckdevelopment.model.Student;
import com.example.fullsatckdevelopment.request.LoginRequest;
import com.example.fullsatckdevelopment.request.RegisterRequest;
import com.example.fullsatckdevelopment.request.UpdateRequest;
import com.example.fullsatckdevelopment.response.GetStudentResponse;
import com.example.fullsatckdevelopment.response.LoginResponse;
import com.example.fullsatckdevelopment.response.RegisterResponse;

import java.util.List;

public interface IStudentService {


    RegisterResponse registerStudent(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    Student addStudent(Student student);

    List<Student> getStudent();

    Student updateStudent(UpdateRequest updateRequest, Long id);

    GetStudentResponse getStudentById(Long id);

    void deleteStudent(Long id);
}
