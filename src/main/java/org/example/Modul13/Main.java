package org.example.Modul13;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.*;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class Main {
    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    public String sendGetRequest(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    public User createUser(String name, String username, String email) {
        String jsonInput = "{" +
                "\"name\": \"" + name + "\"," +
                "\"username\": \"" + username + "\"," +
                "\"email\": \"" + email + "\"" +
                "}";

        try {
            String responseJson = sendPostRequest("/users", jsonInput);
            User user = processUserResponse(responseJson);
            if (user != null) {
                return user;
            } else {
                System.out.println("Failed to create user.");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(User user) {
        String jsonInput = "{" +
                "\"name\": \"" + user.getName() + "\"," +
                "\"username\": \"" + user.getUsername() + "\"," +
                "\"email\": \"" + user.getEmail() + "\"" +
                "}";

        try {
            String responseJson = sendPutRequest("/users/" + user.getId(), jsonInput);
            return responseJson.equals(jsonInput);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            int statusCode = sendDeleteRequest("/users/" + userId);
            return statusCode >= 200 && statusCode < 300;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void getAllUsers() {
        try {
            String responseJson = sendGetRequest("/users");
            JSONArray usersJsonArray = new JSONArray(responseJson);
            for (int i = 0; i < usersJsonArray.length(); i++) {
                JSONObject userJson = usersJsonArray.getJSONObject(i);
                User user = processUserResponse(userJson.toString());
                System.out.println("User: " + user.getName() + ", ID: " + user.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        try {
            String responseJson = sendGetRequest("/users/" + userId);
            return processUserResponse(responseJson);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            String responseJson = sendGetRequest("/users?username=" + username);
            JSONArray usersJsonArray = new JSONArray(responseJson);
            if (usersJsonArray.length() > 0) {
                JSONObject userJson = usersJsonArray.getJSONObject(0);
                return processUserResponse(userJson.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User with username '" + username + "' not found.");
        return null;
    }

    public void getAndSaveCommentsForLastPost(int userId) {
        try {
            String userPostsJson = sendGetRequest("/users/" + userId + "/posts");
            JSONArray userPostsArray = new JSONArray(userPostsJson);

            if (userPostsArray.length() == 0) {
                System.out.println("No posts found for user with ID " + userId);
                return;
            }

            JSONObject lastPost = userPostsArray.getJSONObject(userPostsArray.length() - 1);
            int lastPostId = lastPost.getInt("id");

            String commentsJson = sendGetRequest("/posts/" + lastPostId + "/comments");

            String fileName = "user-" + userId + "-post-" + lastPostId + "-comments.json";
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(commentsJson);
            }

            System.out.println("Comments saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllOpenTasksForUser(int userId) {
        try {
            String tasksJson = sendGetRequest("/users/" + userId + "/todos");
            JSONArray tasksJsonArray = new JSONArray(tasksJson);
            System.out.println("Open tasks for user " + userId + ":");
            for (int i = 0; i < tasksJsonArray.length(); i++) {
                JSONObject taskJson = tasksJsonArray.getJSONObject(i);
                boolean completed = taskJson.getBoolean("completed");
                if (!completed) {
                    String title = taskJson.getString("title");
                    System.out.println("- " + title);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendPostRequest(String endpoint, String jsonInput) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write(jsonInput);
            writer.flush();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    public String sendPutRequest(String endpoint, String jsonInput) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write(jsonInput);
            writer.flush();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    public int sendDeleteRequest(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        return connection.getResponseCode();
    }

    private User processUserResponse(String responseJson) {
        JSONObject userJson = new JSONObject(responseJson);
        int id = userJson.getInt("id");
        String name = userJson.getString("name");
        String username = userJson.getString("username");
        String email = userJson.getString("email");

        return new User(id, name, username, email, null, null, null, null);
    }

    public static void main(String[] args) {
        Main api = new Main();


        User newUser = api.createUser("John Doe", "johndoe", "johndoe@example.com");
        if (newUser != null) {
            System.out.println("User created: " + newUser.getName());
        }


        api.getAndSaveCommentsForLastPost(1);


        api.getAllOpenTasksForUser(1);
    }
}