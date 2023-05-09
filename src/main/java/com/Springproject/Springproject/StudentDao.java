package com.Springproject.Springproject;

import com.Springproject.Springproject.Entity.Marks;
import com.Springproject.Springproject.Entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
public class StudentDao {

    private int studentId;
    private String name;
    private LocalDate dob;
    private String schoolName;
    private String schoolAddress;
    private int attendance;
    private List<MarksDao> marks;
    private String grandTotal;
    private String overallGrade;
    private double percentage;
    private String overallAttendance;
}
