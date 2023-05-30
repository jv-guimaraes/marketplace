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

	public void novaLoja(String nome, String email, String senha, String cnpj, String endereco)
			throws lojaJaCadastradaException {
		Loja novaLoja = new Loja(nome, email, senha, cnpj, endereco);
		if (this.lojas.contains(novaLoja)) {
			throw new lojaJaCadastradaException();
		}
		lojas.add(novaLoja);
		this.write();
	}

	private void write() {
		JsonUtil.writeJSONArray(new JSONArray(this.lojas), "lojas.json");
	}

	private void read() {
		JSONArray jsarray = JsonUtil.readJSONArray("lojas.json");
		for (int i = 0; i < jsarray.length(); i++) {
			this.lojas.add(new Loja(jsarray.getJSONObject(i)));
		}
	}

	public static class lojaJaCadastradaException extends Exception {
	}
}
