package com.ruchi.service.impl;

import com.ruchi.exception.InvalidDataException;
import com.ruchi.exception.UnknownException;
import com.ruchi.model.Student;
import com.ruchi.repository.StudentRepository;
import com.ruchi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultsStudentService implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public boolean saveStudent(Student student) {
        boolean status = false;
        try {
            studentRepository.save(student);
            status = true;
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException(e);
        } catch (Exception e) {
            throw new UnknownException(e);
        }
        return status;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try {
            students = studentRepository.findAll();
        } catch (Exception e) {
            throw new UnknownException(e);
        }
        return students;
    }

    @Override
    public boolean deleteStudent(Integer studentId) {
        boolean status = false;
        try {
            studentRepository.deleteById(studentId);
            status = true;
        } catch (Exception e) {
            throw new UnknownException(e);
        }
        return status;
    }

    @Override
    public Student getStudentById(Integer studentId) {
        Student student = null;
        try {
            student = studentRepository.findById(studentId)
                .orElse(null);
        } catch (Exception e) {
            throw new UnknownException(e);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean status = false;
        try {
            studentRepository.save(student);
            status = true;
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException(e);
        }  catch (Exception e) {
            throw new UnknownException(e);
        }
        return status;
    }
}
