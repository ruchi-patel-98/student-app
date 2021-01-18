package com.ruchi.controller;

import com.ruchi.exception.DataNotFoundException;
import com.ruchi.exception.InvalidDataException;
import com.ruchi.model.Student;
import com.ruchi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("student")
    public boolean saveStudent(@RequestBody Student student) {
        if (student == null || student.getStudentBranch() == null
            || student.getStudentEmail() == null || student.getStudentName() == null) {
            throw new InvalidDataException("Required data missing");
        }
        return studentService.saveStudent(student);
    }

    @GetMapping("student")
    public List<Student> getAllStudents() {
        List<Student> students = studentService.getStudents();
        if (students != null && !students.isEmpty()) {
            return students;
        } else {
            throw new DataNotFoundException();
        }

    }

    @DeleteMapping("student/{studentId}")
    public boolean deleteStudent(@PathVariable("studentId") Integer studentId) {
        if (studentId == null) {
            throw new InvalidDataException("Invalid input provided");
        }
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("student/{studentId}")
    public Student studentByID(@PathVariable("studentId") Integer studentId) {
        if (studentId == null) {
            throw new InvalidDataException("Invalid input provided");
        }
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            return student;
        } else {
            throw new DataNotFoundException();
        }
    }

    @PostMapping("student/{studentId}")
    public boolean updateStudent(@RequestBody Student student, @PathVariable("studentId") Integer studentId) {
        if (studentId == null || student == null || student.getStudentBranch() == null
            || student.getStudentEmail() == null || student.getStudentName() == null) {
            throw new InvalidDataException("Required data missing");
        }
        student.setStudentId(studentId);
        return studentService.updateStudent(student);
    }
}
