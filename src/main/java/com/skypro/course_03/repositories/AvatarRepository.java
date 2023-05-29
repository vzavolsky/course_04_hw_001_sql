package com.skypro.course_03.repositories;

import com.skypro.course_03.entity.Avatar;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent_Id(Long studentId);

    /*@Query(value = "SELECT a.id as id, a.file_path as file_path, a.student_id as student_id FROM avatars as a", nativeQuery = true)
    Collection<Avatar> getAll();*/
}
