package marketplace;

import org.json.JSONObject;

public class Loja {
	private String nome;
	private String email;
	private String senha;
	private String cnpj;
	private String endereco;

	public Loja(String nome, String email, String senha, String cnpj, String endereco) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}
	
	public Loja(JSONObject jo) {
		this.nome = jo.getString("nome");
		this.email = jo.getString("email");
		this.senha = jo.getString("senha");
		this.cnpj = jo.getString("cnpj");
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

	public String getCnpj() {
		return cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (!cnpj.equals(other.cnpj)) {
			return false;
		}
		return true;
	}
	
	
}
