package marketplace;

import java.util.ArrayList;

import org.json.JSONArray;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LojaController {
	private ArrayList<Loja> lojas;
	
	public LojaController() {
		this.lojas = new ArrayList<Loja>();
		this.read();
	}

	public ArrayList<Loja> getLojas() {
		return lojas;
	}

	public void novaLoja(String nome, String email, String senha, String cnpj, String endereco) {
		lojas.add(new Loja(nome, email, senha, cnpj, endereco));
		this.write();
	}
	
	private void write() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("lojas.json"));
			JSONArray jsarray = new JSONArray(this.lojas);
			out.write(jsarray.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void read() {
		try {
			String content = new String(Files.readAllBytes(Paths.get("lojas.json")));
			JSONArray jsarray = new JSONArray(content);
			for (int i = 0; i < jsarray.length(); i++) {
				this.lojas.add(new Loja(jsarray.getJSONObject(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
