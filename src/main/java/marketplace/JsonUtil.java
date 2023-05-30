package marketplace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

public final class JsonUtil {
	public static void writeJSONArray(JSONArray array, String filepath) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
			out.write(array.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONArray readJSONArray(String filepath) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(filepath)));
			return new JSONArray(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
