SELECT s.id, s.name, s.age, f.name FROM students as s JOIN faculties f on f.id = s.faculty_id;
SELECT s.id, s.name, s.age FROM students as s JOIN avatars a on s.id = a.student_id WHERE a.student_id = s.id;