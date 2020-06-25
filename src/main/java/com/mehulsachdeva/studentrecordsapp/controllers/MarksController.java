package com.mehulsachdeva.studentrecordsapp.controllers;

import com.mehulsachdeva.studentrecordsapp.models.Marks;
import com.mehulsachdeva.studentrecordsapp.services.MarksService.MarksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/marks.studentrecords")
public class MarksController {

    @Autowired
    private MarksServiceImpl marksServiceImpl;

    @RequestMapping("/store")
    public Map<String, String> storeMarks(Marks marks) {
        return marksServiceImpl.storeMarks(marks);
    }

    @RequestMapping("/marks/{studentId}")
    public Map<String, String> fetchStudentMarks(@PathVariable("studentId") int studentId) {
        return marksServiceImpl.fetchStudentMarks(studentId);
    }
}
