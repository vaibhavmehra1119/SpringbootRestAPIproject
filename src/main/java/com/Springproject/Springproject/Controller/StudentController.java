package com.Springproject.Springproject.Controller;

import com.Springproject.Springproject.MarksDao;
import com.Springproject.Springproject.Service.StudentService;
import com.Springproject.Springproject.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public String postDetails(@RequestBody StudentDao studentDao){

        List<MarksDao> marks= studentDao.getMarks();

        try{
            return studentService.saveDetails(studentDao,marks);
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }


    }
    @PostMapping("/fetchStudentData")
    public Object getDetails(@RequestBody StudentDao studentDao){

        if(studentDao.getDob()==null||studentDao.getStudentId()==0){
            return "You have not entered student id or dob. Please try again!";
        }
        StudentDao studentWithMarks= studentService.getDetails(studentDao.getStudentId(),studentDao.getDob());
        if(studentWithMarks.getStudentId()==0||studentWithMarks.getDob()==null
                ||!studentWithMarks.getDob().equals(studentDao.getDob())){
            return "Input details are not found. Please check the details.";
        }

        return studentWithMarks;
    }
}
