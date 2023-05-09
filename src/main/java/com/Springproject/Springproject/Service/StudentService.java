package com.Springproject.Springproject.Service;

import com.Springproject.Springproject.Entity.Marks;
import com.Springproject.Springproject.Entity.Student;
import com.Springproject.Springproject.MarksDao;
import com.Springproject.Springproject.Repository.MarksRepo;
import com.Springproject.Springproject.Repository.StudentRepo;
import com.Springproject.Springproject.StudentCompositeKey;
import com.Springproject.Springproject.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MarksRepo marksRepo;


    public String calculateOverallGrade(double percentage){
        if(percentage>85){
            return "A";
        }
        else if(percentage>70)
            return "B";
        else if(percentage>60)
            return "C";
        else if(percentage>40)
            return "D";
        else
            return "F";
    }
    public String calculateGrade(MarksDao subject){
        int obtained_marks= subject.getObtainedMarks();
        if(obtained_marks>=90){
            return "A+";
        }
        else if(obtained_marks>=80&&obtained_marks<=89)
            return "A-";
        else if (obtained_marks>=70&&obtained_marks<=79) {
            return "B+";
        } else if (obtained_marks>=60&&obtained_marks<=69) {
            return "B-";
        } else if (obtained_marks>=50&&obtained_marks<=59) {
            return "C+";
        } else if (obtained_marks>=40&&obtained_marks<=49) {
            return "C-";
        }
        else
            return "F";
    }

    public double calculatePercentage(List<MarksDao> subjects){

        int total=0;
        int totalMaxMarks=0;
        for(MarksDao subject: subjects){
            total+=subject.getObtainedMarks();
            totalMaxMarks+=subject.getMaximumMarks();
        }
        double ans=((total*100)/(double)totalMaxMarks);
        return ans;
    }
    public String calculateGrandTotal(List<MarksDao> subjects){
        int total=0;
        int totalMaxMarks=0;
        for(MarksDao subject: subjects){
            total+=subject.getObtainedMarks();
            totalMaxMarks+=subject.getMaximumMarks();
        }
        return Integer.toString(total)+"/"+Integer.toString(totalMaxMarks);
    }
    public StudentDao getDetails(int studentid, LocalDate dob){

        Student student;

        student = studentRepo.findByStudentCompositeKeyStudentId(studentid);

        if(student==null){
            return new StudentDao();
        }
        List<Marks> marks=marksRepo.findAll();

        StudentDao studentDao= studentToDao(student);
        List<MarksDao> marksDaoList= new ArrayList<MarksDao>();

        for(Marks mark:marks){
            String grade="";

            MarksDao subject=marksToDao(mark);
            if(mark.getStudent()==student){

                //check grade
                grade= calculateGrade(subject);
                subject.setGrade(grade);

                //check status fail or pass
                if(subject.getObtainedMarks()<=39)
                    subject.setStatus("Fail");
                else
                    subject.setStatus("Pass");

                //add the subject details to the marks list of desired student
                marksDaoList.add(subject);
            }
        }
        String grandTotal=calculateGrandTotal(marksDaoList);
        studentDao.setGrandTotal(grandTotal);

        double percentage=calculatePercentage(marksDaoList);
        studentDao.setPercentage(percentage);

        String overallGrade=calculateOverallGrade(percentage);
        studentDao.setOverallGrade(overallGrade);

        studentDao.setOverallAttendance("100/"+Integer.toString(studentDao.getAttendance()));
        studentDao.setMarks(marksDaoList);
        return studentDao;
    }
    public String saveDetails(StudentDao studentDao, List<MarksDao> marksDao){

        Student student = daoToStudent(studentDao);
        if(studentRepo.existsById(student.getStudentCompositeKey())) {
            return "Student Already exists";
        }
        Student savedStudent = studentRepo.save(student);
        for (MarksDao mark : marksDao) {

            Marks mark1 = daoToMarks(mark);
            mark1.setStudent(savedStudent);

            marksRepo.save(mark1);
        }
        return "Saved Successfullly";
    }

    public Student daoToStudent(StudentDao studentDao) {
        Student student = new Student();
        StudentCompositeKey studentCompositeKey= new StudentCompositeKey(studentDao.getStudentId(),studentDao.getDob());
        student.setStudentCompositeKey(studentCompositeKey);
        //student.setStudentId(studentDao.getStudentId());
        student.setName(studentDao.getName());
        //student.setDob(studentDao.getDob());
        student.setAttendance(studentDao.getAttendance());
        student.setSchoolAddress(studentDao.getSchoolAddress());
        student.setSchoolName(studentDao.getSchoolName());
        return student;
    }

    public StudentDao studentToDao(Student student) {
        StudentDao studentDao = new StudentDao();
        StudentCompositeKey studentCompositeKey= student.getStudentCompositeKey();
        studentDao.setStudentId(studentCompositeKey.getStudentId());
        studentDao.setName(student.getName());
        studentDao.setDob(studentCompositeKey.getDob());
        studentDao.setAttendance(student.getAttendance());
        studentDao.setSchoolAddress(student.getSchoolAddress());
        studentDao.setSchoolName(student.getSchoolName());
        return studentDao;
    }
    public Marks daoToMarks(MarksDao marksDao){
        Marks marks= new Marks();
        marks.setSubjectName(marksDao.getSubjectName());
        marks.setMaximumMarks(marksDao.getMaximumMarks());
        marks.setObtainedMarks(marksDao.getObtainedMarks());
        marks.setSubjectCode(marksDao.getSubjectCode());
        return marks;
    }
    public MarksDao marksToDao(Marks marks){
        MarksDao marksDao= new MarksDao();
        marksDao.setSubjectName(marks.getSubjectName());
        marksDao.setMaximumMarks(marks.getMaximumMarks());
        marksDao.setObtainedMarks(marks.getObtainedMarks());
        marksDao.setSubjectCode(marks.getSubjectCode());
        return marksDao;
    }

}