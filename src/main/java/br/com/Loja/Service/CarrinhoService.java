package br.com.Loja.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.Loja.Repository.CarrinhoRepository;
import br.com.Loja.Repository.CategoriaRepository;
import br.com.Loja.Repository.ProdutoRepository;
import br.com.Loja.model.Carrinho;
import br.com.Loja.model.Categoria;
import br.com.Loja.model.Produto;
import br.com.Loja.model.dto.ProdutoSaidaDto;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public String adicionarProdutoAoCarrinho(Long carrinhoId, Long produtoId) {
		Carrinho carrinho = carrinhoRepository.findById(carrinhoId);
		Produto produto = produtoRepository.findById(produtoId);

		if (carrinho == null) {
			return "Carrinho não encontrado";
		}
		if (produto == null) {
			return "Produto não encontrado";
		}

		carrinho.adicionarProduto(produto);
		return "Produto adicionado ao carrinho";
	}

	public Set<Produto> listarProdutosNoCarrinho(Long carrinhoId) {
		Carrinho carrinho = carrinhoRepository.findById(carrinhoId);
		return (carrinho != null) ? carrinho.getProdutos() : Collections.emptySet();
	}

	public String removerProdutoDoCarrinho(Long carrinhoId, Long produtoId) {
		Carrinho carrinho = carrinhoRepository.findById(carrinhoId);
		if (carrinho == null) {
			return "Carrinho não encontrado";
		}
		if (!carrinho.removerProduto(produtoId)) {
			return "Produto não encontrado no carrinho";
		}
		carrinhoRepository.save(carrinho); // Salva o carrinho atualizado
		return "Produto removido do carrinho";
	}
}
