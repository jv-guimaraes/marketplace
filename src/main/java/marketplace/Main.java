package marketplace;

import org.json.*;

public class Main {

	public static void main(String[] args) {
		LojaController lojas = new LojaController();
//		lojas.novaLoja("Teste", "teste@gmail.com", "123", "1234", "rua das almas, 74");
//		lojas.novaLoja("Festa", "festa@gmail.com", "321", "4321", "rua da silva, 81");
		try {
			lojas.novaLoja("Loja dos Pobres", "pobres@gmail.com", "789", "654987", "rua terceira, 42");
		} catch (LojaController.lojaJaCadastradaException e) {
			System.out.println("Loja ja existe no sistema!");
//			e.printStackTrace();
		}
	}

}
