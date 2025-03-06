package br.com.Loja.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Loja.Repository.CarrinhoRepository;
import br.com.Loja.Repository.CategoriaRepository;
import br.com.Loja.Repository.ProdutoRepository;
import br.com.Loja.model.Carrinho;
import br.com.Loja.model.Categoria;
import br.com.Loja.model.Produto;
import br.com.Loja.model.dto.ProdutoEntradaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	// Criar um produto
	public Produto criarProduto(ProdutoEntradaDto produtoEntradaDto) {
		Produto produto = new Produto();
		produto.setNome(produtoEntradaDto.getNome());
		produto.setDescricao(produtoEntradaDto.getDescricao());
		produto.setPreco(produtoEntradaDto.getPreco());

		Produto produtoSalvo = produtoRepository.save(produto);

		return produtoSalvo;
	}

	public void deletarProduto(Long produtoId) {
		Produto produto = produtoRepository.findById(produtoId);
		if (produto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		}
		produtoRepository.delete(produto); // Remover o produto do repositório
	}

	// Associar um produto a uma categoria
	public void associarCategoria(Long produtoId, Long categoriaId) {
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

		produto.setCategoria(categoria); // Associa a categoria ao produto
		produtoRepository.save(produto);
	}

	public void desassociarCategoria(Long produtoId) {
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

		produto.setCategoria(null); // Remove a categoria associada ao produto
		produtoRepository.save(produto);
	}

	// Listar todos os produtos
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

}
