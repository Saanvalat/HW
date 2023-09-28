package org.example.Modul11;

import java.util.stream.Stream;

import static org.example.Modul11.Task4.Random.randomStream;

public class Task4 {

    public class Random {
        public static Stream<Long> randomStream(long a, long c, long m, long seed) {
            return Stream.iterate(seed, x -> (a * x + c) % m);


        }
    }

    public static void main(String[] args) {
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long seed = System.currentTimeMillis();

        Stream<Long> stream = randomStream (a, c, m, seed);
        stream.limit(10).forEach(System.out::println);
    }
}



