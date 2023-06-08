-- liquibase formatted sql

-- changeset editorname:1
/*create table students
(
    id         SERIAL,
    name       char(50),
    age        int,
    faculty_id int
);*/

-- changeset editorname:2
create index students_id_index on students (id);

-- changeset homeworkeditor:3
create index students_name_index on students (name);
create index faculties_nc_index on faculties (name, color);