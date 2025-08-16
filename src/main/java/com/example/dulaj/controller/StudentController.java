package com.example.dulaj.controller;

import com.example.dulaj.dto.StudentDTO;
import com.example.dulaj.service.StudentService;
import jakarta.validation.Valid; // @Valid annotation සඳහා මෙය අවශ්‍යයි
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor Injection: @Autowired එක මෙතනදී අවශ්‍ය නෑ
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    // වැරදි addStudent() method එක ඉවත් කර ඇත
    // duplicate @PostMapping එක ඉවත් කර ඇත

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable long id){
        return studentService.getStudentByID(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable long id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable long id) {
        if (studentService.deleteStudent(id)) {
            return "Student with ID " + id + " has been deleted.";
        } else {
            return "Student not found.";
        }
    }
}