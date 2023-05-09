package com.Springproject.Springproject.Entity;

import com.Springproject.Springproject.StudentCompositeKey;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Students")
@NoArgsConstructor
@Getter
@Setter
public class Student {


    @EmbeddedId
    private StudentCompositeKey studentCompositeKey;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "student_id", nullable=false, length=20)
//    private int studentId;

    @Column(name = "name", nullable=false, length=50)
    private String name;
//
//    @Column(name = "dob", nullable=false)
//    private LocalDate dob;

    @Column(name = "school_name", nullable=false, length=200)
    private String schoolName;

    @Column(name = "school_address", nullable=false, length=300)
    private String schoolAddress;

//    @Column(name = "total", nullable=false)
//    private int total;

    @Column(name = "attendance", nullable=false)
    private int attendance;

}