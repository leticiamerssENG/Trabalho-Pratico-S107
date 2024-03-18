package br.inatel.cdg.compras;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteCarrinhoCompra {


	// Teste inicial básico (Padrão 1)
	@Test
	public void testSomaTotalCompra() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);

		assertEquals(1300, carrinho.somaTotal(),0.01);

	}


	// Teste inicial básico (Padrão 1) + algo diferente (teste valor total de itens) (Padrão 2)
	@Test
	public void testSomaTotalQtd() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);
		assertEquals(10, carrinho.somaTotalItens(),0.01);


	}

	// Teste negativo (Padrão 3) forçando uma situação inesperada
	@Test(expected = NumeroNegativoException.class)
	public void testSomaNegativo() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", -6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", -4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);

	}

	// Teste integração (Padrão 4) 2 classes (Carrinho e Cupom sendo testadas juntas).
	@Test
	public void testIntegracaoCupom() throws NumeroNegativoException {

		Brownie bw1 = new Brownie("Brownie Nutella", 6, 150);
		Brownie bw2 = new Brownie("Brownie Doce de Leite", 4, 100);

		CarrinhoCompra carrinho = new CarrinhoCompra();
		CupomDesconto cupom = new CupomDesconto(0.20);
		carrinho.setCupom(cupom);

		carrinho.adiciona(bw1);
		carrinho.adiciona(bw2);
		carrinho.somaTotal();

		assertEquals(200, carrinho.somaTotal(), 0.1);

	}
    
	// Testa a instanciação de um objeto
	@Test
    public void testCriacaoBrownie() {
        Brownie brownie = new Brownie("Chocolate", 3, 2.5);
        assertNotNull(brownie);
    }
    
	// Teste total de itens no carrinho
	@Test
    public void testSomaDeItens() throws NumeroNegativoException {
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.adiciona(new Brownie("Chocolate", 3, 2.5));
        carrinho.adiciona(new Brownie("Vanilla", 2, 3.0));
        assertEquals(5, carrinho.somaTotalItens(), 0.1);
    }
    
	// Teste criação do Cupom
	@Test
	public void testSetDesconto() {
        CupomDesconto cupom = new CupomDesconto(0.2);
        assertEquals(0.2, cupom.getDesconto(), 0.001);
    }
    
	// Teste com cupom Nulo
	@Test
    public void testSetNullCoupon() throws NumeroNegativoException {
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.adiciona(new Brownie("Chocolate", 3, 2.5));
        carrinho.adiciona(new Brownie("Vanilla", 2, 3.0));
        carrinho.somaTotal();
		carrinho.setCupom(null);
        assertEquals(13.5, carrinho.somaTotal(), 0.1);

	}
    
	// Teste com Cupom Válido
	@Test
    public void testComCupom() throws NumeroNegativoException {
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.adiciona(new Brownie("Chocolate", 3, 2.5));
        carrinho.adiciona(new Brownie("Vanilla", 2, 3.0));
        carrinho.setCupom(new CupomDesconto(0.1));
        assertEquals(12.15, carrinho.somaTotal(), 0.001);
    }
    
	// Teste Set Cupom
	@Test
    public void testCupom() throws NumeroNegativoException {
        CarrinhoCompra carrinho = new CarrinhoCompra();
        carrinho.setCupom(new CupomDesconto(0.5));
        carrinho.adiciona(new Brownie("Chocolate Branco", 3, 2.5));
		carrinho.setCupom(new CupomDesconto(0));
        assertNotEquals(3.75, carrinho.somaTotal(), 0.001);
    }

}

	