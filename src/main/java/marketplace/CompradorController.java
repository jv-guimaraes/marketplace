package marketplace;

import java.util.ArrayList;

import org.json.JSONArray;

public class CompradorController {
	private ArrayList<Comprador> compradores;

	public CompradorController() {
		this.compradores = new ArrayList<Comprador>();
		this.read();
	}

	public ArrayList<Comprador> getCompradores() {
		return compradores;
	}

	public void novoComprador(String nome, String email, String senha, String cpf, String endereco)
			throws compradorJaCadastrado {
		Comprador novoComprador = new Comprador(nome, email, senha, cpf, endereco);
		if (this.compradores.contains(novoComprador)) {
			throw new compradorJaCadastrado();
		}
		compradores.add(novoComprador);
		this.write();
	}

	private void write() {
		JsonUtil.writeJSONArray(new JSONArray(this.compradores), "compradores.json");
	}

	private void read() {
		JSONArray jsarray = JsonUtil.readJSONArray("compradores.json");
		for (int i = 0; i < jsarray.length(); i++) {
			this.compradores.add(new Comprador(jsarray.getJSONObject(i)));
		}
	}

	@SuppressWarnings("serial")
	public static class compradorJaCadastrado extends Exception {
	}
}
