package com.skypro.course_03.services;

import com.skypro.course_03.entity.Faculty;
import com.skypro.course_03.entity.Student;
import com.skypro.course_03.exceptions.StudentNotFoundException;
import com.skypro.course_03.repositories.FacultyRepository;
import com.skypro.course_03.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student add(Student student) {
        student.setId(null);
        student.setFaculty(
                Optional.ofNullable(student.getFaculty())
                        .filter(f -> f.getId() != null)
                        .flatMap(f -> facultyRepository.findById(f.getId()))
                        .orElse(null));
        return studentRepository.save(student);
    }

    public Optional<Student> update(Long id, Student student) {
        return studentRepository.findById(id)
                .map(s -> {
                    s.setName(student.getName());
                    s.setAge(student.getAge());
                    s.setFaculty(
                            Optional.ofNullable(student.getFaculty())
                                    .filter(f -> f.getId() != null)
                                    .flatMap(f -> facultyRepository.findById(f.getId()))
                                    .orElse(null));
                    return studentRepository.save(s);
                });
    }

    public Optional<Student> getById(Long id) {
        logger.debug("Require student width id: {}", id);
        final Optional<Student> student = studentRepository.findById(id);
        logger.debug(
                "Get student with id {} and name {}.\nMethod name is \"{}\"",
                id,
                student.get().getName(),
                Thread.currentThread().getStackTrace()[1]);
        return student;
    }

    public Collection<Student> getAll() {
        logger.debug("Show all students.");
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    public Optional<Student> deleteById(Long id) {
        logger.debug("Delete student width id: {}", id);
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.deleteById(id);
                    return student;
                });
    }

    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.getStudentsByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Optional<Faculty> getStudentFaculties(Long id) {
        return studentRepository.findById(id)
                .map(student -> student.getFaculty());
    }

    public Collection<Student> getStudentsBySQL() {
        return studentRepository.getALlBySqlQuery();
    }

    public Integer getMiddleAgeBySql() {
        return studentRepository.getMiddleAgeBySql();
    }

    public Collection<Student> getLastStudentsBySql(int numOfStudents) {
        return studentRepository.getLastStudentsBySql(numOfStudents);
    }

    public Collection<Student> getStudentsByName(String name) {
        return studentRepository.getStudentsByName(name);
    }

    public Stream<String> nameStartsWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted();
    }

    public Double getAgvAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(StudentNotFoundException::new);
    }
}
