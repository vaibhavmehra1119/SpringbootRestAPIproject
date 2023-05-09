package com.Springproject.Springproject.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marks")
@NoArgsConstructor
@Getter
@Setter
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "student_id"),
            @JoinColumn(
                    name = "dob",
                    referencedColumnName = "dob")
    })
    private Student student;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
//    private Student student;

    @Column(name = "subject_code", nullable=false, length=50)
    private String subjectCode;

    @Column(name = "subject_name", nullable=false)
    private String subjectName;

    @Column(name = "maximum_marks", nullable=false)
    private int maximumMarks;

    @Column(name = "obtained_marks", nullable=false)
    private int obtainedMarks;
}

