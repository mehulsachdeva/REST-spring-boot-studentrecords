package com.mehulsachdeva.studentrecordsapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Marks {
    private int studentId;
    private String maths;
    private String science;
    private String english;
}
