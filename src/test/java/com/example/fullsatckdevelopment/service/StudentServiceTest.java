package com.example.fullsatckdevelopment.service;

import com.example.fullsatckdevelopment.exception.StudentAlreadyExistsException;
import com.example.fullsatckdevelopment.model.Student;
import com.example.fullsatckdevelopment.repository.StudentRepository;
import com.example.fullsatckdevelopment.request.RegisterRequest;
import com.example.fullsatckdevelopment.response.GetStudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void registerStudentTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Coutinho");
        registerRequest.setLastName("Dacruz");
        registerRequest.setEmail("Coutinho@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setDepartment("Engineering");
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
        Student student = new Student();
        student.setEmail("test@example.com");
        studentRepository.save(student);

        assertThrows(StudentAlreadyExistsException.class, () -> studentService.addStudent(student));
    }

    @Test
    public void getStudentTest() {
        Student student1 = new Student();
        student1.setEmail("test1@example.com");
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setEmail("test2@example.com");
        studentRepository.save(student2);

        assertEquals(2, studentService.getStudent().size());
    }

    @Test
    public void updateStudentTest() {
        Student student = new Student();
        student.setEmail("test@example.com");
        studentRepository.save(student);

        student.setEmail("updated@example.com");
        Student updatedStudent = studentService.updateStudent(student, student.getId());

        assertEquals("updated@example.com", updatedStudent.getEmail());
    }

    @Test
    public void getStudentByIdTest() {
        Student student = new Student();
        student.setEmail("test@example.com");
        studentRepository.save(student);

        GetStudentResponse response = studentService.getStudentById(student.getId());

        assertNotNull(response);
        assertEquals(student.getId(), response.getId());
    }

    @Test
    @Transactional
    public void deleteStudentTest() {
        Student student = new Student();
        student.setEmail("test@example.com");
        studentRepository.save(student);

        studentService.deleteStudent(student.getId());

        assertFalse(studentRepository.existsById(student.getId()));
    }

}