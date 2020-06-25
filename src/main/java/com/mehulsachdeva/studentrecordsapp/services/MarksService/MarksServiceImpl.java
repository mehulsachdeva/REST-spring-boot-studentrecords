package com.mehulsachdeva.studentrecordsapp.services.MarksService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.studentrecordsapp.models.Marks;
import com.mehulsachdeva.studentrecordsapp.models.StudentMarks;
import com.mehulsachdeva.studentrecordsapp.util.MarksUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MarksServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MarksUtil marksUtil;

    public Map<String, String> storeMarks(Marks marks) {
        Map<String, String> response = new LinkedHashMap<>();
        final String STORE_MARKS_QUERY = Constants.STORE_MARKS_QUERY;
        Object params[] = new Object[] { marks.getStudentId(), marks.getMaths(), marks.getScience(), marks.getEnglish() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        try {
            int rows = jdbcTemplate.update(STORE_MARKS_QUERY, params, types);
            if(rows > 0) {
                response.put("STATUS", Constants.SUCCESS_STATUS);
                response.put("RESPONSE", Constants.INSERT_SUCCESS_RESPONSE);
                response.put("ERROR", "");
            }else {
                response.put("STATUS", Constants.SUCCESS_STATUS);
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

    public Map<String, String> fetchStudentMarks(int studentId) {
        Map<String, String> response = new LinkedHashMap<>();
        Object params[] = new Object[] { studentId };
        try {
            final String FETCH_STUDENT_MARKS_QUERY = Constants.FETCH_STUDENT_MARKS_QUERY;
            StudentMarks studentMarks = (StudentMarks)jdbcTemplate.queryForObject(FETCH_STUDENT_MARKS_QUERY, marksUtil.getStudentMarksMap(), params);
            response.put("STATUS", Constants.SUCCESS_STATUS);
            response.put("RESPONSE", new ObjectMapper().writeValueAsString(studentMarks));
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
