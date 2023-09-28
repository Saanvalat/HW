package org.example.Modul11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Task5 {
    public static <Object> Stream<Object> zip(Stream<Integer> first, Stream<String> second){
        Iterator<Integer> iterator1 = first.iterator();
        Iterator<String> iterator2 = second.iterator();

        List<Object> result = new ArrayList<>();

        while (iterator1.hasNext() && iterator2.hasNext()){
           result.add((Object) iterator1.next());
            result.add((Object) iterator2.next());
        }
        return result.stream();
    }

    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(1,2,3,4,5);
        Stream<String> stream2 = Stream.of("a","b","c","d");

        Stream<Object> objectStream = zip(stream1,stream2);
        objectStream.forEach(System.out::println);
    }


}
