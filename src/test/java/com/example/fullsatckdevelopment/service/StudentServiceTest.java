package com.example.fullsatckdevelopment.service;

import com.example.fullsatckdevelopment.exception.StudentAlreadyExistsException;
import com.example.fullsatckdevelopment.model.Student;
import com.example.fullsatckdevelopment.request.RegisterRequest;
import com.example.fullsatckdevelopment.request.UpdateRequest;
import com.example.fullsatckdevelopment.response.GetStudentResponse;
import com.example.fullsatckdevelopment.response.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {


   @Autowired
    StudentService studentService;

    @Test
    public void registerStudentTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Jessica");
        registerRequest.setLastName("Ashley");
        registerRequest.setEmail("jessica@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Frontend");
        registerRequest.setPassword("password");
        var response = studentService.registerStudent(registerRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
    }

    @Test
    public void addStudentAlreadyExistsTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Jessica");
        registerRequest.setLastName("Ashley");
        registerRequest.setEmail("hellojessica@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Frontend");
        registerRequest.setPassword("password");

        assertThrows(StudentAlreadyExistsException.class, () -> studentService.registerStudent(registerRequest));
    }

    @Test
    public void getStudentTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Bob");
        registerRequest.setLastName("Marley");
        registerRequest.setEmail("marley@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Music");
        var response = studentService.registerStudent(registerRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();

        RegisterRequest registerRequestTwo = new RegisterRequest();
        registerRequestTwo.setFirstName("Lampard");
        registerRequestTwo.setLastName("Frank");
        registerRequestTwo.setEmail("frank@gmail.com");
        registerRequestTwo.setPassword("password");
        registerRequestTwo.setDepartment("Web dev");

        RegisterResponse registerResponse = studentService.registerStudent(registerRequestTwo);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isNotNull();

        List<Student> allStudents = studentService.getStudent();
//        assertEquals(4, studentService.getStudent().size());
        assertThat(allStudents).hasSizeBetween(1,6);
    }


    @Test
    public void updateStudentTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Kinzy");
        registerRequest.setLastName("Kinzy");
        registerRequest.setEmail("kinzy@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Frontend");
        registerRequest.setPassword("password");
        var registerResponse = studentService.registerStudent(registerRequest);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setId(registerResponse.getId());
        updateRequest.setFirstName("Boy");
        updateRequest.setLastName("Girl");
        updateRequest.setPassword("12345");
        updateRequest.setDepartment("Music");
        updateRequest.setEmail("kinzy@gmail.com");
        Student updatedStudent = studentService.updateStudent(updateRequest, registerResponse.getId());
        assertNotNull(updatedStudent);

        assertEquals(updateRequest.getFirstName(), "Boy");
        assertEquals(updateRequest.getLastName(), "Girl");

    }

    @Test
    public void getStudentByIdTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Dom");
        registerRequest.setLastName("Coutinho");
        registerRequest.setEmail("Coutinho@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Frontend");
        registerRequest.setPassword("password");
        var registerResponse = studentService.registerStudent(registerRequest);



        GetStudentResponse response = studentService.getStudentById(registerResponse.getId());
        assertThat(response).isNotNull();
        assertEquals("Frontend", response.getDepartment());
        assertEquals("Coutinho@gmail.com", response.getEmail());

    }

    @Test
    public void deleteStudentTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Favour");
        registerRequest.setLastName("White");
        registerRequest.setEmail("fwhite@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Frontend");
        registerRequest.setPassword("password");
        var registerResponse = studentService.registerStudent(registerRequest);


        GetStudentResponse response = studentService.getStudentById(registerResponse.getId());


        studentService.deleteStudent(response.getId());
    }

}