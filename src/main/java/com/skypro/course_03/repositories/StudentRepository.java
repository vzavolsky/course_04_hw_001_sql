package com.skypro.course_03.repositories;

import com.skypro.course_03.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> getStudentsByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    //@Query(value = "SELECT s.id, s.name, s.age FROM students as s, faculties as f WHERE s.faculty_id = f.id AND s.faculty_id = :id", nativeQuery = true)
    Collection<Student> findAllByFaculty_Id(Long id);

    @Query(value = "SELECT * FROM students", nativeQuery = true)
    Collection<Student> getALlBySqlQuery();

    @Query(value = "SELECT AVG(age) FROM students", nativeQuery = true)
    Integer getMiddleAgeBySql();

    // Сортировал список дваждый умышленно, чтобы не выводить с последнего id. Лучшего решения не нашёл.
    @Query(value = "SELECT * FROM (SELECT * FROM students as s ORDER BY s.id DESC LIMIT :numOfStudents) as s ORDER BY s.id ASC", nativeQuery = true)
    Collection<Student> getLastStudentsBySql(int numOfStudents);

    Collection<Student> getStudentsByName(String name);
}
