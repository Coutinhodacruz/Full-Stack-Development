package com.example.fullsatckdevelopment.service;

import com.example.fullsatckdevelopment.exception.StudentAlreadyExistsException;
import com.example.fullsatckdevelopment.exception.StudentNotFoundException;
import com.example.fullsatckdevelopment.model.Student;
import com.example.fullsatckdevelopment.repository.StudentRepository;
import com.example.fullsatckdevelopment.request.LoginRequest;
import com.example.fullsatckdevelopment.request.RegisterRequest;
import com.example.fullsatckdevelopment.response.GetStudentResponse;
import com.example.fullsatckdevelopment.response.LoginResponse;
import com.example.fullsatckdevelopment.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;


//    private final PasswordEncoder passwordEncoder;


    public RegisterResponse registerStudent(RegisterRequest registerRequest) {
        if (studentAlreadyExists(registerRequest.getEmail())) {
            throw new StudentAlreadyExistsException(registerRequest.getEmail() + " already exists");
        }

        Student student = new Student();
        student.setFirstName(registerRequest.getFirstName());
        student.setLastName(registerRequest.getLastName());
        student.setEmail(registerRequest.getEmail());
        student.setDepartment(registerRequest.getDepartment());

//
//        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
//        student.setPassword(encodedPassword);
        studentRepository.save(student);
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Student registered successfully");

        return response;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Student student = studentRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

//        if (!passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())) {
//            throw new InvalidCredentialsException("Invalid password");
//        }

        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful");

        return response;
    }




    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail() + "already exists!");
        }

        return studentRepository.save(student);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();

    }


    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(studentOne ->{
            studentOne.setFirstName(student.getFirstName());
            studentOne.setLastName(student.getLastName());
            studentOne.setEmail(student.getEmail());
            studentOne.setPassword(student.getPassword());
            studentOne.setDepartment(studentOne.getDepartment());

            return studentRepository.save(studentOne);

        }).orElseThrow(() -> new StudentNotFoundException("Sorry this student could not be found"));
    }

    @Override
    public GetStudentResponse getStudentById(Long id) {
        Optional<Student> found = studentRepository.findById(id);
        Student student = found.orElseThrow(() -> new StudentNotFoundException("Student not found with ID::  " + id));
        GetStudentResponse getStudentResponse = buildGetUserResponse(student);
        return getStudentResponse;

    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);
    }


    private static GetStudentResponse buildGetUserResponse(Student savedStudent){
        return GetStudentResponse.builder()
                .id(savedStudent.getId())
                .password(savedStudent.getPassword())
                .fullName(getFullName(savedStudent))
                .email(savedStudent.getEmail())
                .department(savedStudent.getDepartment())
                .build();
    }

    private static String getFullName(Student savedStudent){
        return savedStudent.getFirstName() + " " + savedStudent.getLastName();
    }
}








