package com.mehulsachdeva.studentrecordsapp.services.MarksService;

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
    public static final String STORE_MARKS_QUERY = "INSERT INTO marks VALUES (?, ?, ?, ?)";
    public static final String FETCH_STUDENT_MARKS_QUERY = "SELECT S.student_id, name, maths, science, english FROM marks M RIGHT JOIN student S ON M.student_id = S.student_id WHERE S.student_id = ?";
}
