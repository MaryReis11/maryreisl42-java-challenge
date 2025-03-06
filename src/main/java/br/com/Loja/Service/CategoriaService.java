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

	public boolean podeRemoverCategoria(Long categoriaId) {
		// Verificar se a categoria está associada a produtos ou carrinhos
		List<Produto> produtos = produtoRepository.findAll();
		for (Produto produto : produtos) {
			if (produto.getCategoria() != null && produto.getCategoria().getId().equals(categoriaId)) {
				return false; // Categoria está associada a um produto
			}
		}

		List<Carrinho> carrinhos = carrinhoRepository.findAll();
		for (Carrinho carrinho : carrinhos) {
			if (carrinho.getProdutos().stream()
					.anyMatch(produto -> produto.getCategoria().getId().equals(categoriaId))) {
				return false; // Categoria está associada a um carrinho
			}
		}

		return true; // Se não estiver associada a produtos ou carrinhos
	}

	public void removerCategoria(Long categoriaId) {
		categoriaRepository.delete(categoriaId);
	}

	public Optional<Categoria> findCategoriaById(Long categoriaId) {
		return categoriaRepository.findById(categoriaId);
	}
}
