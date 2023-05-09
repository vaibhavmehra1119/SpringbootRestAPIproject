package com.Springproject.Springproject;

import com.Springproject.Springproject.Entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarksDao {

    private String subjectCode;
    private String subjectName;
    private int maximumMarks;
    private int obtainedMarks;

    private String grade;
    private String status;

}
