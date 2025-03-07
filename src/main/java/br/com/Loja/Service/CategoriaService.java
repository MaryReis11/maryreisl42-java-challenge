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
import br.com.Loja.exception.TabelaDeErros;
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
	        // Usando TabelaDeErros.CATEGORIA_NAO_ENCONTRADA
	        throw new ResponseStatusException(
	            TabelaDeErros.CATEGORIA_NAO_ENCONTRADA.getCodigoHttp(),
	            TabelaDeErros.CATEGORIA_NAO_ENCONTRADA.getMensagem()
	        );
	    }

	    Categoria categoria = categoriaOpt.get();

	    // Verificar se há produtos associados a essa categoria
	    boolean temProdutosAssociados = produtoRepository.findAll().stream()
	        .anyMatch(produto -> produto.getCategoria().getId().equals(categoriaId));

	    if (temProdutosAssociados) {
	        // Usando TabelaDeErros.CATEGORIA_ASSOCIADA_PRODUTO
	        throw new ResponseStatusException(
	            TabelaDeErros.CATEGORIA_ASSOCIADA_PRODUTO.getCodigoHttp(),
	            TabelaDeErros.CATEGORIA_ASSOCIADA_PRODUTO.getMensagem()
	        );
	    }

	    // Remover a categoria
	    categoriaRepository.delete(categoria);
	}

	
}
