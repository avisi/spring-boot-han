package nl.han.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

    @Column
    private String naam;

    @Id
    private int nummer;

    public Student() {
    }

    public Student(int nummer, String naam) {
        this.nummer = nummer;
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public int getNummer() {
        return nummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        if (nummer != student.nummer) {
            return false;
        }
        return naam.equals(student.naam);
    }

    @Override
    public int hashCode() {
        int result = naam.hashCode();
        result = 31 * result + nummer;
        return result;
    }
}
