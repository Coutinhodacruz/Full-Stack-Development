package com.example.fullsatckdevelopment.service;

import com.example.fullsatckdevelopment.exception.StudentAlreadyExistsException;
import com.example.fullsatckdevelopment.model.Student;
import com.example.fullsatckdevelopment.repository.StudentRepository;
import com.example.fullsatckdevelopment.request.RegisterRequest;
import com.example.fullsatckdevelopment.request.UpdateRequest;
import com.example.fullsatckdevelopment.response.GetStudentResponse;
import com.example.fullsatckdevelopment.response.RegisterResponse;
import com.example.fullsatckdevelopment.response.UpdateResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void registerStudentTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Coutinho");
        registerRequest.setLastName("Dacruz");
        registerRequest.setEmail("Coutinho@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Engineering");
        registerRequest.setPassword("password");
        var response = studentService.registerStudent(registerRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
    }

//    @Test
//    public void getStudentByIdTest(){
//        GetStudentResponse response = studentService.getStudentById(500L);
//        assertThat(response).isNotNull();
//    }

    @Test
    public void addStudentAlreadyExistsTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Coutinho");
        registerRequest.setLastName("Dacruz");
        registerRequest.setEmail("Coutinho@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Engineering");

        assertThrows(StudentAlreadyExistsException.class, () -> studentService.registerStudent(registerRequest));
    }

    @Test
    public void getStudentTest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Bob");
        registerRequest.setLastName("Emmanuel");
        registerRequest.setEmail("emmanuel@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Agricultural");
        var response = studentService.registerStudent(registerRequest);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();

        RegisterRequest registerRequestTwo = new RegisterRequest();
        registerRequestTwo.setFirstName("Gerrad");
        registerRequestTwo.setLastName("Steven");
        registerRequestTwo.setEmail("steven@gmail.com");
        registerRequestTwo.setPassword("password");
        registerRequestTwo.setDepartment("Finance");

        RegisterResponse registerResponse = studentService.registerStudent(registerRequestTwo);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isNotNull();

        assertEquals(3, studentService.getStudent().size());
    }


    @Test
    public void updateStudentTest() {
        // Given
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setId(3L);
        updateRequest.setFirstName("Boy");
        updateRequest.setLastName("Girl");
        updateRequest.setPassword("12345");
        updateRequest.setDepartment("Music");
        updateRequest.setEmail("kinzy@gmail.com");

        Student existingStudent = new Student(); // Assuming you have a Student entity
        existingStudent.setId(3L);
        existingStudent.setFirstName("Gerrad");
        existingStudent.setLastName("Steven");
        existingStudent.setPassword("Password");
        existingStudent.setDepartment("Finance");
        existingStudent.setEmail("steven@gmail.com");

        when(studentRepository.findById(3L)).thenReturn(Optional.of(existingStudent));

        // When
        Student updatedStudent = studentService.updateStudent(updateRequest, 3L);

        // Then
        assertNotNull(updatedStudent);
        assertEquals(updateRequest.getFirstName(), updatedStudent.getFirstName());
        assertEquals(updateRequest.getLastName(), updatedStudent.getLastName());
        assertEquals(updateRequest.getEmail(), updatedStudent.getEmail());
        assertEquals(updateRequest.getPassword(), updatedStudent.getPassword());
        assertEquals(updateRequest.getDepartment(), updatedStudent.getDepartment());

        // Verify that the save method was called with the updated student
        verify(studentRepository, times(1)).save(updatedStudent);
    }

//    @Test
//    public void updateStudentTest() {
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.setId(5L);
//        updateRequest.setFirstName("Boy");
//        updateRequest.setLastName("Girl");
//        updateRequest.setPassword("12345");
//        updateRequest.setDepartment("Music");
//        updateRequest.setEmail("kinzy@gmail.com");
//
//        var response = studentService.updateStudent(updateRequest, 5L);
//        assertThat(response).isNotNull();
//
//        GetStudentResponse userResponse = studentService.getStudentById(3L);
//        String fullName = userResponse.getFullName();
//        String expectedFullName = updateRequest.getFirstName() +
//                " " +
//                updateRequest.getLastName();
//
//        assertThat(fullName).isEqualTo(expectedFullName);
//
//    }

    @Test
    public void getStudentByIdTest() {
        // Create a Student object with a valid ID
        Student student = new Student();
        student.setId(1L); // Set a valid non-null ID
        student.setEmail("Coutinho@gmail.com");

        // Mock the behavior of your student repository to return the student when findById is called with a valid ID
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Call the service method
        GetStudentResponse response = studentService.getStudentById(student.getId());

        // Assert the response
        assertThat(response).isNotNull();
        assertEquals(student.getId(), response.getId());
        assertEquals(student.getEmail(), response.getEmail());

    }

    @Test
    public void deleteStudentTest() {
        Student student = new Student();
        student.setEmail("steven@gmail.com");

        studentService.deleteStudent(student.getId());

        assertFalse(studentRepository.existsById(student.getId()));
    }

}