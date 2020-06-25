package com.mehulsachdeva.studentrecordsapp.util;

import com.mehulsachdeva.studentrecordsapp.models.StudentMarks;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class MarksUtil {
    public RowMapper<StudentMarks> getStudentMarksMap() {
        RowMapper<StudentMarks> studentMarks = (rs, numRow) -> {
            StudentMarks studentMark = new StudentMarks();
            studentMark.setStudentId(rs.getInt("student_id"));
            studentMark.setName(rs.getString("name"));
            studentMark.setMaths(rs.getString("maths") == null ? "" : rs.getString("maths"));
            studentMark.setScience(rs.getString("science") == null ? "" : rs.getString("science"));
            studentMark.setEnglish(rs.getString("english") == null ? "" : rs.getString("english"));
            return studentMark;
        };
        return studentMarks;
    }
}
