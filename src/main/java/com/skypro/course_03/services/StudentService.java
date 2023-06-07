package com.skypro.course_03.services;

import com.skypro.course_03.entity.Faculty;
import com.skypro.course_03.entity.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentService {
    Collection<Student> getAll();

    Optional<Student> getById(Long id);

    Student add(Student student);

    Optional<Student> update(Long id, Student student);

    Optional<Student> deleteById(Long id);

    Collection<Student> getStudentsByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    Optional<Faculty> getStudentFaculties(Long id);

    Collection<Student> getStudentsBySQL();

    Integer getMiddleAgeBySql();

    Collection<Student> getLastStudentsBySql(int num);

    Collection<Student> getStudentsByName(String name);
}
