package com.mehulsachdeva.studentrecordsapp.services.MarksService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.studentrecordsapp.models.Marks;
import com.mehulsachdeva.studentrecordsapp.models.StudentMarks;
import com.mehulsachdeva.studentrecordsapp.util.MarksUtil;
import com.mehulsachdeva.studentrecordsapp.util.ResponseBuilder;
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

    @Autowired
    private ResponseBuilder responseBuilder;

    public Map<String, String> storeMarks(Marks marks) {
        final String STORE_MARKS_QUERY = Constants.STORE_MARKS_QUERY;
        Object params[] = new Object[] { marks.getStudentId(), marks.getMaths(), marks.getScience(), marks.getEnglish() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        try {
            int rows = jdbcTemplate.update(STORE_MARKS_QUERY, params, types);
            if(rows > 0) {
                return responseBuilder.createResponse(
                        Constants.SUCCESS_STATUS,
                        Constants.INSERT_SUCCESS_RESPONSE,
                        ""
                );
            }else {
                return responseBuilder.createResponse(
                        Constants.SUCCESS_STATUS,
                        Constants.INSERT_FAILURE_RESPONSE,
                        Constants.FAILURE_ERROR
                );
            }
        }catch(Exception e) {
            return responseBuilder.createResponse(
                    Constants.FAILURE_STATUS,
                    Constants.INSERT_FAILURE_RESPONSE,
                    Constants.EXCEPTION_RAISED_ERROR + String.valueOf(e)
            );
        }
    }

    public Map<String, String> fetchStudentMarks(int studentId) {
        Map<String, String> response = new LinkedHashMap<>();
        Object params[] = new Object[] { studentId };
        try {
            final String FETCH_STUDENT_MARKS_QUERY = Constants.FETCH_STUDENT_MARKS_QUERY;
            StudentMarks studentMarks = (StudentMarks)jdbcTemplate.queryForObject(FETCH_STUDENT_MARKS_QUERY, marksUtil.getStudentMarksMap(), params);
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    new ObjectMapper().writeValueAsString(studentMarks),
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
