package com.Springproject.Springproject.Repository;

import com.Springproject.Springproject.Entity.Student;
import com.Springproject.Springproject.StudentCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepo extends JpaRepository<Student, StudentCompositeKey> {

    Student findByStudentCompositeKeyStudentId(int id);

}
