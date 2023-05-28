package marketplace;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		Gson gson = new Gson();
		Teste teste = new Teste(69, "Testando");
		System.out.println(gson.toJson(teste));
	}

}
