package marketplace;

import org.json.*;

import marketplace.LojaController.cnpjJaCadastrado;

public class Main {

	public static void main(String[] args) {
		LojaController lojas = new LojaController();
//		lojas.novaLoja("Teste", "teste@gmail.com", "123", "1234", "rua das almas, 74");
//		lojas.novaLoja("Festa", "festa@gmail.com", "321", "4321", "rua da silva, 81");
		try {
			lojas.novaLoja("Caro", "caro@gmail.com", "771", "12321", "rua chique, 400");
		} catch (cnpjJaCadastrado e) {
			System.out.println("Usuario ja existe no sistema!");
		}
		System.out.println(new JSONArray(lojas.getLojas()));
	}

}
