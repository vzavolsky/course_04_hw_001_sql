SELECT s.id, s.name, s.age, f.name FROM students as s LEFT JOIN faculties f on f.id = s.faculty_id;
SELECT s.id, s.name, s.age FROM students as s inner join avatars a on s.id = a.student_id;