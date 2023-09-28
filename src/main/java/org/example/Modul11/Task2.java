package org.example.Modul11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task2 {
    public static List<String> fixNames (List<String> inputList){
        List<String> results = inputList.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.comparing(String::toString).reversed())
                .collect(Collectors.toList());

    return results;
    }

    public static void main(String[] args) {

        List<String> inputList = Arrays.asList("Ivan", "Andriy", "Petro", "Yakiv", "Matviy", "Pavlo", "Pylyp");
        List<String> fixedNames = fixNames(inputList);

        for (String string : fixedNames) {
            System.out.println(string);
        }
    }
}

