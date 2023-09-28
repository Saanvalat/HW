package org.example.Modul11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Task3 {


    public static void main(String[] args) {
        String [] someArray = {"1, 2, 0", "4, 5"};
         int[][] parsedArray = new int[someArray.length][];


        int i;
        for (i = 0; i < someArray.length; i++){
             String[] parts = someArray[i].split(", ");
             parsedArray[i] = new int[parts.length];


            int j;
            for (j = 0; j < parts.length; j++){
                parsedArray[i][j] = Integer.parseInt(parts[j]);
            }
        }


        int divideArray = parsedArray.length / 2;

        int[][] leftArray = Arrays.copyOfRange(parsedArray, 0, divideArray);
        int[][] rightArray = Arrays.copyOfRange(parsedArray, divideArray, parsedArray.length);

//        System.out.println(Arrays.deepToString(leftArray) + "  " + Arrays.deepToString(rightArray));

        StringBuilder singleArray = new StringBuilder();

        for (int k = 0; k < parsedArray.length; k++) {
            for (int j = 0; j < parsedArray[k].length; j++) {
                singleArray.append(parsedArray[k][j]);
                singleArray.append(" ");

            }

        }
//       System.out.println("singleArray = " + singleArray);



             List<Integer> integerList = Arrays.stream(parsedArray)
                     .flatMapToInt(Arrays::stream)
                     .boxed()
                     .sorted()
                     .collect(Collectors.toList());

        String result = integerList.toString();
        result = result.replace("[", "\"").replace("]","\"");
        System.out.println("integerList = " + result);


    }

}






