package br.com.Loja.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.Loja.model.Carrinho;

public class CarrinhoRepositoryTest {

	@Autowired
    private CarrinhoRepository carrinhoRepository;  // Injeção do repositório

    @Test
    void criarCarrinho_DeveCriarNovoCarrinhoComIdUnico() {
        // Testa a criação de carrinho
        Carrinho carrinho1 = carrinhoRepository.criarCarrinho();
        Carrinho carrinho2 = carrinhoRepository.criarCarrinho();

        assertNotNull(carrinho1);
        assertNotNull(carrinho2);
        assertNotEquals(carrinho1.getId(), carrinho2.getId());
    }

    @Test
    void findById_DeveRetornarCarrinhoExistente() {
        // Cria um carrinho e salva
        Carrinho carrinho = carrinhoRepository.criarCarrinho();
        carrinhoRepository.save(carrinho);

        // Busca o carrinho pelo ID
        Carrinho carrinhoEncontrado = carrinhoRepository.findById(carrinho.getId()).orElse(null);

        assertNotNull(carrinhoEncontrado);
        assertEquals(carrinho.getId(), carrinhoEncontrado.getId());
    }
}
