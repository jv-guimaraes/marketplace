package marketplace;

import java.util.ArrayList;

import org.json.*;

public class Main {

	public static void main(String[] args) {
		Teste teste1 = new Teste(25, "Testando uma string");
		Teste teste2 = new Teste(40, "Testando string");
		Teste teste3 = new Teste(123, "Blah blah blah");
		ArrayList<Teste> lista = new ArrayList<Teste>();
		lista.add(teste1);
		lista.add(teste2);
		lista.add(teste3);
		JSONArray jo = new JSONArray(lista);
		System.out.println(jo);
	}

}
