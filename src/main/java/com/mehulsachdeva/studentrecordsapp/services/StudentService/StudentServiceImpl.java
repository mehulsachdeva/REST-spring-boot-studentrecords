package com.mehulsachdeva.studentrecordsapp.services.StudentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.studentrecordsapp.models.Student;
import com.mehulsachdeva.studentrecordsapp.util.ResponseBuilder;
import com.mehulsachdeva.studentrecordsapp.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentUtil studentUtil;

    @Autowired
    private ResponseBuilder responseBuilder;

    public Map<String, String> storeStudent(Student student) {
        final String STORE_STUDENT = Constants.STORE_STUDENT_QUERY;
        Object params[] = new Object[] { student.getName(), student.getAge() };
        int[] types = new int[] {Types.VARCHAR, Types.INTEGER };
        try {
            int rows = jdbcTemplate.update(STORE_STUDENT, params, types);
            if(rows == 1) {
                return responseBuilder.createResponse(
                        Constants.SUCCESS_STATUS,
                        Constants.INSERT_SUCCESS_RESPONSE,
                        ""
                );
            }else {
                return responseBuilder.createResponse(
                        Constants.FAILURE_STATUS,
                        Constants.INSERT_FAILURE_RESPONSE,
                        Constants.FAILURE_ERROR
                );
            }
        }catch(Exception e) {
            return responseBuilder.createResponse(Constants.FAILURE_STATUS, Constants.INSERT_FAILURE_RESPONSE, Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e));
        }
    }

    public Map<String, String> fetchStudents() {
        try {
            final String FETCH_STUDENTS_QUERY = Constants.FETCH_STUDENTS_QUERY;
            List<Student> students = jdbcTemplate.query(FETCH_STUDENTS_QUERY, studentUtil.getStudentMap());
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    new ObjectMapper().writeValueAsString(students),
                    ""
            );
        }catch(EmptyResultDataAccessException e) {
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    "[]",
                    ""
            );
        }catch(Exception e) {
            return responseBuilder.createResponse(
                    Constants.FAILURE_STATUS,
                    Constants.FETCH_FAILURE_RESPONSE,
                    Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e)
            );
        }
    }

    public Map<String, String> fetchStudentById(int studentId) {
        Object params[] = new Object[] { studentId };
        try {
            final String FETCH_STUDENT_BY_ID_QUERY = Constants.FETCH_STUDENT_BY_ID_QUERY;
            Student student = (Student)jdbcTemplate.queryForObject(FETCH_STUDENT_BY_ID_QUERY, studentUtil.getStudentMap(), params);
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    new ObjectMapper().writeValueAsString(student),
                    ""
            );
        }catch(EmptyResultDataAccessException e) {
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    Constants.NO_RECORD_FOUND,
                    ""
            );
        }catch(Exception e) {
            return responseBuilder.createResponse(
                    Constants.FAILURE_STATUS,
                    Constants.FETCH_FAILURE_RESPONSE,
                    Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e)
            );
        }
    }
}
