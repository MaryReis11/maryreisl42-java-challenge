package br.com.Loja.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Loja.Service.ProdutoService;
import br.com.Loja.model.Produto;
import br.com.Loja.model.dto.ProdutoEntradaDto;
import br.com.Loja.model.dto.ProdutoSaidaDto;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping
	public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoEntradaDto produtoEntradaDto) {
		try {
			produtoService.criarProduto(produtoEntradaDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{produtoId}/categoria/{categoriaId}")
	public ResponseEntity<String> associarCategoria(@PathVariable Long produtoId, @PathVariable Long categoriaId) {
		try {
			produtoService.associarCategoria(produtoId, categoriaId);
			return ResponseEntity.ok("Categoria associada com sucesso ao produto.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return ResponseEntity.ok(produtos); // Retorna a lista de produtos com status 200
	}

	@DeleteMapping("/{produtoId}")
	public ResponseEntity<String> deletarProduto(@PathVariable Long produtoId) {
		try {
			produtoService.deletarProduto(produtoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto excluído com sucesso.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
