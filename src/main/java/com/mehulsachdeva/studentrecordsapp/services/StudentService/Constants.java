package com.mehulsachdeva.studentrecordsapp.services.StudentService;

public class Constants {
    public static final String INSERT_SUCCESS_RESPONSE = "Inserted Successfully";
    public static final String INSERT_FAILURE_RESPONSE = "Failed To Insert";

    public static final String FETCH_FAILURE_RESPONSE = "Failed To Fetch";
    public static final String NO_RECORD_FOUND = "No Student Found";

    public static final String SUCCESS_STATUS = "Success";
    public static final String FAILURE_STATUS = "Failure";
    public static final String FAILURE_ERROR = "Some Error Occurred. Try Again!";
    public static final String EXCEPTION_RAISED_ERROR = "Exception Raised: ";

    //SQL Query
    public static final String STORE_STUDENT_QUERY = "INSERT INTO student (name, age) VALUES (?, ?)";
    public static final String FETCH_STUDENTS_QUERY = "SELECT * FROM student";
    public static final String FETCH_STUDENT_BY_ID_QUERY = "SELECT * FROM student WHERE student_id = ?";
}
