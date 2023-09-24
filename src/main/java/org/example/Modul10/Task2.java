package org.example.Modul10;


import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {

    try (FileInputStream fis = new FileInputStream("file1.txt");
    InputStreamReader inputStreamReader = new InputStreamReader(fis);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
    String line;

        List<User> userList = new ArrayList<>();

        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null){
            String[] text = line.split(" ");

            if (text.length == 2) {
                String name = text[0];
                Integer age = Integer.parseInt(text[1]);

                User user = new User(name, age);
                userList.add(user);


            }
            }
        Gson gson = new Gson();
        String json= gson.toJson(userList);
        try (FileWriter fileWriter = new FileWriter("user.json")){
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
       }

    } catch (IOException e) {
        e.printStackTrace();


    }
    }

}













