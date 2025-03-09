package br.com.Loja.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.Loja.Repository.CarrinhoRepository;
import br.com.Loja.model.Carrinho;

@SpringBootTest
public class CarrinhoRepositoryTest {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Test
    void criarCarrinho_DeveCriarNovoCarrinhoComIdUnico() {
        Carrinho carrinho1 = carrinhoRepository.criarCarrinho();
        Carrinho carrinho2 = carrinhoRepository.criarCarrinho();

        assertNotNull(carrinho1);
        assertNotNull(carrinho2);
        assertNotEquals(carrinho1.getId(), carrinho2.getId());
    }

    @Test
    void findById_DeveRetornarCarrinhoExistente() {
        Carrinho carrinho = carrinhoRepository.criarCarrinho();
        carrinhoRepository.save(carrinho);

        Carrinho carrinhoEncontrado = carrinhoRepository.findById(carrinho.getId()).orElse(null);

        assertNotNull(carrinhoEncontrado);
        assertEquals(carrinho.getId(), carrinhoEncontrado.getId());
    }
}