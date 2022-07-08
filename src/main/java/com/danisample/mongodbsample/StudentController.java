package com.danisample.mongodbsample;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
@Slf4j
public class StudentController {

    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        log.info("Getting all Students");
        return studentService.getStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student) {
        log.info("Adding Student: " + student);
        studentService.addStudent(student);
    }

    @PutMapping(value = "/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        log.info("Updating Student: " + student);
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudent(@PathVariable String id) {
        log.info("Deleting Student with id: " + id);
        studentService.deleteStudent(id);
    }
}
