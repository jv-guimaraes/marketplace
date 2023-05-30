package marketplace;

import java.util.ArrayList;

import org.json.JSONArray;

public class CompradorController {
	private ArrayList<Loja> compradores;

	public CompradorController() {
		this.compradores = new ArrayList<Loja>();
		this.read();
	}

	public ArrayList<Loja> getLojas() {
		return compradores;
	}

	public void novoComprador(String nome, String email, String senha, String cpf, String endereco)
			throws compradorJaCadastrado {
		Loja novaLoja = new Loja(nome, email, senha, cpf, endereco);
		if (this.compradores.contains(novaLoja)) {
			throw new compradorJaCadastrado();
		}
		compradores.add(novaLoja);
		this.write();
	}

	private void write() {
		JsonUtil.writeJSONArray(new JSONArray(this.compradores), "compradores.json");
	}

	private void read() {
		JSONArray jsarray = JsonUtil.readJSONArray("compradores.json");
		for (int i = 0; i < jsarray.length(); i++) {
			this.compradores.add(new Loja(jsarray.getJSONObject(i)));
		}
	}

	@SuppressWarnings("serial")
	public static class compradorJaCadastrado extends Exception {
	}
}
