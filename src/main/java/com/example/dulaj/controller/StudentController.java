package com.example.dulaj.controller;


import com.example.dulaj.dto.StudentDTO;
import com.example.dulaj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students") // මේ controller එකේ හැම URL එකක්ම /students වලින් පටන් ගන්නේ
public class StudentController {

    @Autowired // මේ මැජික් එකෙන් StudentService එක controller එකට auto connect වෙනවා
    private StudentService studentService;

    // 1. READ: ඉන්න හැම ශිෂ්‍යයෙක්ගෙම විස්තර ගන්න
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 2. CREATE: අලුත් ශිෂ්‍යයෙක්ව එකතු කරන්න
    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    // 3. READ: එක ශිෂ්‍යයෙක්ගේ විස්තර ID එකෙන් හොයන්න
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable long id){
        return studentService.getStudentByID(id);
    }

    // 4. UPDATE: ඉන්න ශිෂ්‍යයෙක්ගේ විස්තර අලුත් කරන්න
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    // 5. DELETE: ශිෂ්‍යයෙක්ව list එකෙන් අයින් කරන්න
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable long id) {
        if (studentService.deleteStudent(id)) {
            return "Student with ID " + id + " has been deleted.";
        } else {
            return "Student not found.";
        }
    }
}