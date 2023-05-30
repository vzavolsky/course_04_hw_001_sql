/* Не уверен, что это правильное решение. */
create sequence "age" minvalue 16;
alter sequence "age" owned by students.age;

/* Пример из лекции не отрабатывает */
ALTER TABLE students
    ADD CONSTRAINT age_constraint CHECK (age >= 16);

alter table students
    alter column name set not null;

alter table students
    add unique (name);

alter table faculties
    add unique (name, color);

alter table students
    alter column age set default 20;