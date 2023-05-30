package marketplace;

import marketplace.CompradorController.compradorJaCadastrado;

public class Main {

	public static void main(String[] args) {
		LojaController lojas = new LojaController();
		try {
			lojas.novaLoja("Loja dos Pobres", "pobres@gmail.com", "789", "654987", "rua terceira, 42");
		} catch (LojaController.lojaJaCadastradaException e) {
			System.out.println("Loja ja existe no sistema!");
		}
		
		CompradorController compradores = new CompradorController();
		try {
			compradores.novoComprador("Maria", "maria@gmail.com", "098", "567", "Rua da maria, 111");
		} catch (compradorJaCadastrado e) {
			 System.out.println("Comprador ja existe no sistema!");
		}
		
	}

}
