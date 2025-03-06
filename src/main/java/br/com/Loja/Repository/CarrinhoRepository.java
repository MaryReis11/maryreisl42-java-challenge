package br.com.Loja.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.Loja.model.Carrinho;
import br.com.Loja.model.Produto;

@Repository
public class CarrinhoRepository {

	private Map<Long, Carrinho> carrinhos = new HashMap<>();
	private Long idCounter = 1L;

	public Carrinho findById(Long id) {
		return carrinhos.get(id);
	}

	public Carrinho criarCarrinho() {
		Carrinho novoCarrinho = new Carrinho(idCounter++);
		carrinhos.put(novoCarrinho.getId(), novoCarrinho);
		return novoCarrinho;
	}

	public List<Carrinho> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removerProduto(Long produtoId) {
		return produtos.removeIf(produto -> produto.getId().equals(produtoId));
	}
}
