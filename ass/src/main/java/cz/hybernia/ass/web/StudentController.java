package cz.hybernia.ass.web;

import cz.hybernia.ass.dao.StudentsDao;
import cz.hybernia.ass.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private StudentsDao studentsDao;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentsDao.getAllStudents();
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") int id) {

        return new ResponseEntity<>(studentsDao.getStudent(id), HttpStatus.OK);
    }
}
