package marketplace;

import org.json.JSONObject;

public class Comprador {
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String endereco;
	
	public Comprador(String nome, String email, String senha, String cpf, String endereco) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public Comprador(JSONObject jo) {
		this.nome = jo.getString("nome");
		this.email = jo.getString("email");
		this.senha = jo.getString("senha");
		this.cpf = jo.getString("cpf");
		this.endereco = jo.getString("endereco");
		
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}
}
