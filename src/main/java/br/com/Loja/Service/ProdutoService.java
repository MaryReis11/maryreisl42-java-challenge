package br.com.Loja.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Loja.Repository.CarrinhoRepository;
import br.com.Loja.Repository.CategoriaRepository;
import br.com.Loja.Repository.ProdutoRepository;
import br.com.Loja.exception.TabelaDeErros;
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
	private final CarrinhoRepository carrinhoRepository;

	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.carrinhoRepository = new CarrinhoRepository();
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
	        // Usando TabelaDeErros.PRODUTO_NAO_ENCONTRADO
	        throw new ResponseStatusException(
	            TabelaDeErros.PRODUTO_NAO_ENCONTRADO.getCodigoHttp(), 
	            TabelaDeErros.PRODUTO_NAO_ENCONTRADO.getMensagem()
	        );
	    }

	    // Verifica se o produto está em algum carrinho
	    boolean estaEmCarrinho = carrinhoRepository.findAll().stream()
	        .anyMatch(carrinho -> carrinho.getProdutos().contains(produto));

	    if (estaEmCarrinho) {
	        // Usando TabelaDeErros.PRODUTO_NO_CARRINHO_NAO_REMOVIVEL
	        throw new ResponseStatusException(
	            TabelaDeErros.PRODUTO_NO_CARRINHO_NAO_REMOVIVEL.getCodigoHttp(), 
	            TabelaDeErros.PRODUTO_NO_CARRINHO_NAO_REMOVIVEL.getMensagem()
	        );
	    }

	    produtoRepository.delete(produto);
	}

	public Produto associarCategoria(Long produtoId, Long categoriaId) {
	    // Buscando o produto no repositório
	    Produto produto = produtoRepository.findById(produtoId);
	    if (produto == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
	    }

	    // Verificando se o produto está no carrinho
	    boolean estaEmCarrinho = carrinhoRepository.findAll().stream()
	        .anyMatch(carrinho -> carrinho.getProdutos().contains(produto));

	    if (estaEmCarrinho) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não pode ser alterada, produto está no carrinho");
	    }

	    // Buscando a categoria com Optional
	    Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
	    if (!categoriaOptional.isPresent()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
	    }

	    Categoria categoria = categoriaOptional.get(); // Pega a categoria presente

	    // Associando a categoria ao produto
	    produto.setCategoria(categoria);
	    return produtoRepository.save(produto);
	}

	public Produto desassociarCategoria(Long produtoId) {
	    Produto produto = produtoRepository.findById(produtoId);

	    if (produto == null) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
	    }

	    // Verificando se o produto está no carrinho
	    boolean estaEmCarrinho = carrinhoRepository.findAll().stream()
	        .anyMatch(carrinho -> carrinho.getProdutos().contains(produto));

	    if (estaEmCarrinho) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não pode ser removida, produto está no carrinho");
	    }

	    if (produto.getCategoria() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já está sem categoria");
	    }

	    produto.setCategoria(null); // Remove a categoria associada
	    return produtoRepository.save(produto);
	}

	// Listar todos os produtos
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}
	public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
	// Método correto para encontrar o produto
    public Produto findById(Long id) {
        return produtoRepository.findById(id); // Aqui usamos o método do repositório
    }

}
