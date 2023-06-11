package com.skypro.course_03.repositories;

import com.skypro.course_03.dto.AvatarDto;
import com.skypro.course_03.entity.Avatar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent_Id(Long studentId);

    /*@Query(value = "SELECT a.id as id, a.file_path as file_path, a.student_id as student_id FROM avatars as a", nativeQuery = true)
    Collection<Avatar> getAll();*/

    @Query(
        """
            SELECT new com.skypro.course_03.dto.AvatarDto(
            a.id,
            a.fileSize,
            a.mediaType,
            'http://localhost:8080/avatar/fs?studentId=' || a.student.id
            ) FROM Avatar as a
        """)
    Page<AvatarDto> getPage(Pageable pageable);
}
