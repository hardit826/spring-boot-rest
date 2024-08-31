package com.hardit_code.spring_boot_rest.rest;

import com.hardit_code.spring_boot_rest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //@PostConstruct is called once when the bean is loaded. Load student data here
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Hardit", "Singh"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("John", "Wick"));
    }

    //Endpoint for /students to return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    // To retrieve single student /student/{studentid}
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - "+studentId);
        }
        return theStudents.get(studentId);
    }


}

