package com.mehulsachdeva.studentrecordsapp.util;

import com.mehulsachdeva.studentrecordsapp.models.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentUtil {
    public RowMapper<Student> getStudentMap() {
        RowMapper<Student> students = (rs, numRow) -> {
            Student student = new Student();
            student.setStudentId(rs.getInt("student_id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            return student;
        };
        return students;
    }
}
