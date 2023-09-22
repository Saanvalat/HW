package org.example.Modul10;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Task3 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("word.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;

            TreeMap<String, Integer> wordsQuantity = new TreeMap<>();

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    wordsQuantity.put(word, wordsQuantity.getOrDefault(word, 0) + 1);
                }
            }
            for (Map.Entry<String, Integer> entry : wordsQuantity.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis !=null){
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
