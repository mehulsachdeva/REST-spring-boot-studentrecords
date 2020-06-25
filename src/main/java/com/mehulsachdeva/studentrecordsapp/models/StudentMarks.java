package com.mehulsachdeva.studentrecordsapp.models;

public class StudentMarks {
    private int studentId;
    private String name;
    private String maths;
    private String science;
    private String english;

    public StudentMarks() {
    }

    public StudentMarks(int studentId, String name, String maths, String science, String english) {
        this.studentId = studentId;
        this.name = name;
        this.maths = maths;
        this.science = science;
        this.english = english;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaths() {
        return maths;
    }

    public void setMaths(String maths) {
        this.maths = maths;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
