package com.mehulsachdeva.studentrecordsapp.models;

public class Marks {
    private int studentId;
    private String maths;
    private String science;
    private String english;

    public Marks() {
    }

    public Marks(int studentId, String maths, String science, String english) {
        this.studentId = studentId;
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
