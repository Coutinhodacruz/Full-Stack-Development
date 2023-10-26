package com.example.fullsatckdevelopment.controller;


import com.example.fullsatckdevelopment.model.Student;
import com.example.fullsatckdevelopment.request.RegisterRequest;
import com.example.fullsatckdevelopment.request.UpdateRequest;
import com.example.fullsatckdevelopment.response.GetStudentResponse;
import com.example.fullsatckdevelopment.response.RegisterResponse;
import com.example.fullsatckdevelopment.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {


    private final IStudentService studentServices;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){
        var response = studentServices.registerStudent(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

//    @GetMapping
//    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
//        var response = studentServices.login(loginRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        return new ResponseEntity<>(studentServices.getStudent(), HttpStatus.FOUND);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentServices.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody UpdateRequest updateRequest, @PathVariable Long id){
        return studentServices.updateStudent(updateRequest,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentServices.deleteStudent(id);
    }



    @GetMapping("/student/{id}")
    public ResponseEntity<GetStudentResponse> getStudentById(@PathVariable Long id){
        GetStudentResponse response = studentServices.getStudentById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

}
