package br.com.Loja.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Loja.model.Carrinho;
import br.com.Loja.model.Produto;
import br.com.Loja.Repository.CarrinhoRepository;
import br.com.Loja.Service.CarrinhoService;
import br.com.Loja.model.dto.ProdutoSaidaDto;

@RestController
@RequestMapping("/api/v1/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@PostMapping
	public ResponseEntity<Carrinho> criarCarrinho() {
		Carrinho novoCarrinho = carrinhoRepository.criarCarrinho();
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCarrinho);
	}

	@PostMapping("/{carrinhoId}/produto/{produtoId}")
	public ResponseEntity<String> adicionarProduto(@PathVariable Long carrinhoId, @PathVariable Long produtoId) {
		String resultado = carrinhoService.adicionarProdutoAoCarrinho(carrinhoId, produtoId);
		return ResponseEntity.ok(resultado);
	}

	@GetMapping("/{carrinhoId}/produtos")
	public ResponseEntity<Set<Produto>> listarProdutos(@PathVariable Long carrinhoId) {
		Set<Produto> produtos = carrinhoService.listarProdutosNoCarrinho(carrinhoId);
		return ResponseEntity.ok(produtos);
	}

	@DeleteMapping("/{carrinhoId}/produto/{produtoId}")
	public ResponseEntity<String> removerProduto(@PathVariable Long carrinhoId, @PathVariable Long produtoId) {
		String resultado = carrinhoService.removerProdutoDoCarrinho(carrinhoId, produtoId);
		return ResponseEntity.ok(resultado);
	}

}
