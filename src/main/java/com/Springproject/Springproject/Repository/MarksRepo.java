package com.Springproject.Springproject.Repository;

import com.Springproject.Springproject.Entity.Marks;
import com.Springproject.Springproject.Entity.Student;
import com.Springproject.Springproject.MarksDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarksRepo extends JpaRepository<Marks,Long> {

}
