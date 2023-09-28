package org.example.Modul11;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Ivan", "Andriy", "Petro", "Yakiv", "Matviy", "Pavlo", "Pylyp");

   List<String> filteredNames = IntStream.range(0, names.size())
                    .filter(index -> index % 2 == 0)
                    .mapToObj(index -> (index + 1) + ". " + names.get(index))
                    .collect(Collectors.toList());

   String result = String.join(", ", filteredNames);
        System.out.println(result);


    }
}
