package br.com.Loja.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.Loja.Repository.CarrinhoRepository;
import br.com.Loja.Repository.CategoriaRepository;
import br.com.Loja.Repository.ProdutoRepository;
import br.com.Loja.model.Carrinho;
import br.com.Loja.model.Categoria;
import br.com.Loja.model.Produto;
import br.com.Loja.model.dto.CategoriaEntradaDto;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	private final ProdutoRepository produtoRepository;
	private final CarrinhoRepository carrinhoRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
			CarrinhoRepository carrinhoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
		this.carrinhoRepository = carrinhoRepository;
	}

	public Categoria criarCategoria(CategoriaEntradaDto categoriaEntradaDto) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaEntradaDto.getNome());
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}

	public void removerCategoria(Long categoriaId) {
	    // Buscar categoria pelo ID
	    Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoriaId);

	    // Verificar se a categoria existe
	    if (!categoriaOpt.isPresent()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.");
	    }

	    Categoria categoria = categoriaOpt.get();

	    // Verificar se há produtos associados a essa categoria
	    boolean temProdutosAssociados = produtoRepository.findAll().stream()
	        .anyMatch(produto -> produto.getCategoria().getId().equals(categoriaId));

	    if (temProdutosAssociados) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
	            "Não é possível excluir a categoria, pois ela está associada a produtos.");
	    }

	    // Remover a categoria
	    categoriaRepository.delete(categoria);
	}

	
}
