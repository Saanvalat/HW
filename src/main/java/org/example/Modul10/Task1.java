package org.example.Modul10;

import java.io.*;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("file.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = reader.readLine()) != null) {
                 if (isValidPhoneNumber(line)) {
                    System.out.println(line);
                }
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

public static boolean isValidPhoneNumber(String number){
        String regex = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        return number.matches(regex);

    }
}
