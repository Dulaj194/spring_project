package com.example.dulaj.service;

import com.example.dulaj.dto.StudentDTO;
import com.example.dulaj.entity.Student;
import com.example.dulaj.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    // ---------------- DTO <-> Entity conversion helpers ----------------
    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setContactNo(student.getContactNo());
        return dto;
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId()); // may be null for new student
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setContactNo(dto.getContactNo());
        return student;
    }

    // ---------------- Service methods ----------------

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepo.save(student);
        return convertToDTO(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentByID(long id) {
        Student student = studentRepo.findById(id).orElse(null);
        return (student != null) ? convertToDTO(student) : null;
    }

    public StudentDTO updateStudent(long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepo.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setFirstName(studentDTO.getFirstName());
            existingStudent.setLastName(studentDTO.getLastName());
            existingStudent.setEmail(studentDTO.getEmail());
            existingStudent.setAge(studentDTO.getAge());
            existingStudent.setContactNo(studentDTO.getContactNo());

            Student updatedStudent = studentRepo.save(existingStudent);
            return convertToDTO(updatedStudent);
        }
        return null;
    }

    public boolean deleteStudent(long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
