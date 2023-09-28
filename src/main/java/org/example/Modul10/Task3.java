package org.example.Modul10;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("word.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;

            Map<String, Integer> wordsQuantity = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    wordsQuantity.put(word, wordsQuantity.getOrDefault(word, 0) + 1);
                }
            }

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordsQuantity.entrySet());

            Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                    return entry2.getValue().compareTo(entry1.getValue());
                }
            });


            for (Map.Entry<String, Integer> entry : sortedEntries) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}