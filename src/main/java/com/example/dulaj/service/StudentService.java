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

    // CREATE: අලුත් ශිෂ්‍යයෙක්ව එකතු කරනවා
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setAge(studentDTO.getAge());
        student.setContactNo(studentDTO.getContactNo());

        Student savedStudent = studentRepo.save(student);

        studentDTO.setId(savedStudent.getId());
        return studentDTO;
    }

    // READ: ඉන්න හැම ශිෂ්‍යයෙක්ගෙම විස්තර ගන්නවා
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream()
                .map(student -> {
                    StudentDTO dto = new StudentDTO();
                    dto.setId(student.getId());
                    dto.setFirstName(student.getFirstName());
                    dto.setLastName(student.getLastName());
                    dto.setEmail(student.getEmail());
                    dto.setAge(student.getAge());
                    dto.setContactNo(student.getContactNo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // READ: ID එකෙන් ශිෂ්‍යයෙක්ව හොයනවා
    public StudentDTO getStudentByID(long id) {
        Student student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            StudentDTO dto = new StudentDTO();
            dto.setId(student.getId());
            dto.setFirstName(student.getFirstName());
            dto.setLastName(student.getLastName());
            dto.setEmail(student.getEmail());
            dto.setAge(student.getAge());
            dto.setContactNo(student.getContactNo());
            return dto;
        }
        return null;
    }

    // UPDATE: ශිෂ්‍යයෙක්ගේ විස්තර අලුත් කරනවා
    public StudentDTO updateStudent(long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepo.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setFirstName(studentDTO.getFirstName());
            existingStudent.setLastName(studentDTO.getLastName());
            existingStudent.setEmail(studentDTO.getEmail());
            existingStudent.setAge(studentDTO.getAge());
            existingStudent.setContactNo(studentDTO.getContactNo());

            Student updatedStudent = studentRepo.save(existingStudent);

            StudentDTO updatedDTO = new StudentDTO();
            updatedDTO.setId(updatedStudent.getId());
            updatedDTO.setFirstName(updatedStudent.getFirstName());
            updatedDTO.setLastName(updatedStudent.getLastName());
            updatedDTO.setEmail(updatedStudent.getEmail());
            updatedDTO.setAge(updatedStudent.getAge());
            updatedDTO.setContactNo(updatedStudent.getContactNo());
            return updatedDTO;
        }
        return null;
    }

    // DELETE: ශිෂ්‍යයෙක්ව මකනවා
    public boolean deleteStudent(long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}