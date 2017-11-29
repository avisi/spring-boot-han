package nl.han.student;

import java.util.List;

public interface StudentService {

    List<Student> all();
    void save(Student student);
}
