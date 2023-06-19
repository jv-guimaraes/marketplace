package infrastructure.jsonFiles;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;

public class JsonFileUtil {
    public static JSONArray loadJsonArray(String jsonFilePath) {
        try {
            File file = new File(jsonFilePath);
            if (!file.exists()) {
                return new JSONArray();
            }

            String json = readFileContent(file);
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static void saveJsonArray(JSONArray jsonArray, String jsonFilePath) {
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
