package com.skypro.course_03.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.stream.Stream;

@Service
public class InfoService {

    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);
    private StopWatch stopWatch = new StopWatch();

    public void compareParallelAndPlainStream() {
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

}
