package com.mehulsachdeva.studentrecordsapp.services.StudentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.studentrecordsapp.models.Student;
import com.mehulsachdeva.studentrecordsapp.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentUtil studentUtil;

    public Map<String, String> storeStudent(Student student) {
        Map<String, String> response = new LinkedHashMap<>();
        final String STORE_STUDENT = Constants.STORE_STUDENT_QUERY;
        Object params[] = new Object[] { student.getName(), student.getAge() };
        int[] types = new int[] {Types.VARCHAR, Types.INTEGER };
        try {
            int rows = jdbcTemplate.update(STORE_STUDENT, params, types);
            if(rows == 1) {
                response.put("STATUS", Constants.SUCCESS_STATUS);
                response.put("RESPONSE", Constants.INSERT_SUCCESS_RESPONSE);
                response.put("ERROR", "");
            }else {
                response.put("STATUS", Constants.FAILURE_STATUS);
                response.put("RESPONSE", Constants.INSERT_FAILURE_RESPONSE);
                response.put("ERROR", Constants.FAILURE_ERROR);
            }
            return response;
        }catch(Exception e) {
            response.put("STATUS", Constants.FAILURE_STATUS);
            response.put("RESPONSE", Constants.INSERT_FAILURE_RESPONSE);
            response.put("ERROR", Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e));
            return response;
        }
    }

    public Map<String, String> fetchStudents() {
        Map<String, String> response = new LinkedHashMap<>();
        try {
            final String FETCH_STUDENTS_QUERY = Constants.FETCH_STUDENTS_QUERY;
            List<Student> students = jdbcTemplate.query(FETCH_STUDENTS_QUERY, studentUtil.getStudentMap());
            response.put("STATUS", Constants.SUCCESS_STATUS);
            response.put("RESPONSE", new ObjectMapper().writeValueAsString(students));
            response.put("ERROR", "");
            return response;
        }catch(EmptyResultDataAccessException e) {
            response.put("STATUS", Constants.SUCCESS_STATUS);
            response.put("RESPONSE", "[]");
            response.put("ERROR", "");
            return response;
        }catch(Exception e) {
            response.put("STATUS", Constants.FAILURE_STATUS);
            response.put("RESPONSE", Constants.FETCH_FAILURE_RESPONSE);
            response.put("ERROR", Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e));
            return response;
        }
    }

    public Map<String, String> fetchStudentById(int studentId) {
        Map<String, String> response = new LinkedHashMap<>();
        Object params[] = new Object[] { studentId };
        try {
            final String FETCH_STUDENT_BY_ID_QUERY = Constants.FETCH_STUDENT_BY_ID_QUERY;
            Student student = (Student)jdbcTemplate.queryForObject(FETCH_STUDENT_BY_ID_QUERY, studentUtil.getStudentMap(), params);
            response.put("STATUS", Constants.SUCCESS_STATUS);
            response.put("RESPONSE", new ObjectMapper().writeValueAsString(student));
            response.put("ERROR", "");
            return response;
        }catch(EmptyResultDataAccessException e) {
            response.put("STATUS", Constants.SUCCESS_STATUS);
            response.put("RESPONSE", Constants.NO_RECORD_FOUND);
            response.put("ERROR", "");
            return response;
        }catch(Exception e) {
            response.put("STATUS", Constants.FAILURE_STATUS);
            response.put("RESPONSE", Constants.FETCH_FAILURE_RESPONSE);
            response.put("ERROR", Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e));
            return response;
        }
    }
}
