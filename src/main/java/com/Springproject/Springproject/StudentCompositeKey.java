package com.Springproject.Springproject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class StudentCompositeKey implements Serializable {
    @Column(name="student_id",nullable = false)
    private int studentId;

    @Column(name="dob",nullable = false)
    private LocalDate dob;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCompositeKey that = (StudentCompositeKey) o;
        return getStudentId() == that.getStudentId() && Objects.equals(getDob(), that.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getDob());
    }
}
