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
	    Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(carrinhoId);
	    Produto produto = produtoRepository.findById(produtoId);

	    if (!carrinhoOpt.isPresent()) {
	        return "Carrinho não encontrado";
	    }
	    if (produto == null) {
	        return "Produto não encontrado";
	    }
	    if (produto.getCategoria() == null) {
	        return "Produto não está associado a nenhuma categoria";
	    }

	    Carrinho carrinho = carrinhoOpt.get();
	    carrinho.adicionarProduto(produto);
	    
	    // Salvar a alteração no repositório
	    carrinhoRepository.save(carrinho);

	    return "Produto adicionado ao carrinho";
	}

	public String removerProdutoDoCarrinho(Long carrinhoId, Long produtoId) {
	    // Busca o carrinho pelo ID
	    Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(carrinhoId);
	    
	    // Verifica se o carrinho foi encontrado
	    if (!carrinhoOpt.isPresent()) {
	        return "Carrinho não encontrado";
	    }
	    
	    Carrinho carrinho = carrinhoOpt.get();

	    // Verifica se o carrinho está finalizado
	    if (carrinho.isFinalizado()) {
	        return "Carrinho finalizado. Não é possível remover produtos.";
	    }

	    // Busca o produto no carrinho (supondo que o método "removerProduto" no Carrinho recebe o produto em vez de apenas o ID)
	    Produto produto = produtoRepository.findById(produtoId);
	    if (produto == null) {
	        return "Produto não encontrado";
	    }

	    // Remover o produto
	    carrinho.removerProduto(produto);
	    
	    // Salva a mudança no repositório
	    carrinhoRepository.save(carrinho);
	    
	    return "Produto removido do carrinho";
	}
	public Set<Produto> listarProdutosNoCarrinho(Long carrinhoId) {
	    Optional<Carrinho> carrinhoOpt = carrinhoRepository.findById(carrinhoId);

	    if (!carrinhoOpt.isPresent()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado");
	    }

	    Carrinho carrinho = carrinhoOpt.get();
	    return carrinho.getProdutos();  // Retorna o conjunto de produtos
	}
}
