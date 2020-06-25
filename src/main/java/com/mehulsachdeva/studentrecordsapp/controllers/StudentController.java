package com.mehulsachdeva.studentrecordsapp.controllers;

import com.mehulsachdeva.studentrecordsapp.models.Student;
import com.mehulsachdeva.studentrecordsapp.services.StudentService.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/students.studentrecords")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @RequestMapping("/store")
    public Map<String, String> storeStudent(Student student) {
        return studentServiceImpl.storeStudent(student);
    }

    @RequestMapping("/students")
    public Map<String, String> fetchStudents() {
        return studentServiceImpl.fetchStudents();
    }

    @RequestMapping("/student/{studentId}")
    public Map<String, String> fetchStudentById(@PathVariable("studentId") int studentId) {
        return studentServiceImpl.fetchStudentById(studentId);
    }
}
