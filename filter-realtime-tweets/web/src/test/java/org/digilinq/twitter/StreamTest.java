package org.digilinq.twitter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class StreamTest {
    @Test
    void getAverageOfDoubleStream() {
        Assertions.assertEquals(5.5f,
                IntStream.range(0, 10)
                        .map(i -> i + 1)
                        .boxed()
                        .mapToDouble(Integer::doubleValue)
                        .average()
                        .orElse(Double.NaN)
        );
    }
}
