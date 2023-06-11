package com.skypro.course_03.services;

import com.skypro.course_03.entity.Student;
import com.skypro.course_03.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class InfoService {

    private final StudentRepository studentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    public InfoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private int couter = 0;

    public void compareParallelAndPlainStream() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("plain stream");
        Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        stopWatch.stop();

        stopWatch.start("parallel stream");
        long sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        stopWatch.stop();
        LOG.info("Result is {}; {}", sum, stopWatch.prettyPrint());
    }

    public void checkParallelStream() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("plain stream");
        Stream.iterate(1L, a -> a + 1)
                .limit(10_000L)
                .reduce(0L, (a, b) -> {
                    long s = 0;
                    for (int i = 0; i < a + b; i++) {
                        s += 1;
                    }
                    return s;
                });
        stopWatch.stop();

        stopWatch.start("parallel stream");
        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(10_000L)
                .parallel()
                .reduce(0L, (a, b) -> a + b);
        stopWatch.stop();
        LOG.info("Result is {}; {}", sum, stopWatch.prettyPrint());
    }


    // ДЗ 4.6
    // Код после просмотра видео лекции - начало.
    public void threadsOfStudents() {
        List<String> listOfStudents = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        printListItem(0, listOfStudents);
        printListItem(1, listOfStudents);

        new Thread(() -> {
            printListItem(2, listOfStudents);
            printListItem(3, listOfStudents);
            printListItem(4, listOfStudents);
        }).start();

        new Thread(() -> {
            printListItem(5, listOfStudents);
            printListItem(6, listOfStudents);
        }).start();
    }


    public void syncThreadsOfStudents() {
        List<String> listOfStudents = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        syncPrintListItem(listOfStudents);
        syncPrintListItem(listOfStudents);

        new Thread(() -> {
            syncPrintListItem(listOfStudents);
            syncPrintListItem(listOfStudents);
            syncPrintListItem(listOfStudents);
        }).start();

        new Thread(() -> {
            syncPrintListItem(listOfStudents);
            syncPrintListItem(listOfStudents);
        }).start();
    }

    private void printListItem(int id, List<String> listOfStudents) {
        sleep(100);
        System.out.printf("%d: %s\n", id, listOfStudents.get(id));
    }

    private synchronized void syncPrintListItem(List<String> listOfStudents) {
        printListItem(couter++ % listOfStudents.size(), listOfStudents);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // Код после просмотра видео лекции - Конец.

    // Код после просмотра разбора - начало.
    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 7)).getContent();

        printStudents(students.subList(0, 2));
        new Thread(() -> printStudents(students.subList(2, 4))).start();
        new Thread(() -> printStudents(students.subList(4, 7))).start();
    }

    private void printStudents(List<Student> students) {
        for (Student student : students) {
            LOG.info(student.getName());
        }
    }

    private synchronized void printStudentsSync(List<Student> students, int limit) {
        IntStream.range(0, limit)
                .forEach(index -> {
                    LOG.info(students.get(couter++ % students.size()).getName());
                });
    }

    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 7)).getContent();

        printStudentsSync(students, 2);
        new Thread(() -> printStudentsSync(students, 2)).start();
        new Thread(() -> printStudentsSync(students, 3)).start();
    }
    // Код после просмотра разбора - конец.
}
