package nl.han.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> all() {
        return (List<Student>) repository.findAll();
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }
}
