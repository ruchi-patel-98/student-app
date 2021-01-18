package com.ruchi.service;

import com.ruchi.model.Student;

import java.util.List;

public interface StudentService {

    boolean saveStudent(Student student);

    List<Student> getStudents();

    boolean deleteStudent(Integer studentId);

    Student getStudentById(Integer studentId);

    boolean updateStudent(Student student);
}
