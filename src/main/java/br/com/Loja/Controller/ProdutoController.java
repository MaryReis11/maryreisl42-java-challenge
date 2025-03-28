package br.com.Loja.Controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import br.com.Loja.Service.ProdutoService;
import br.com.Loja.model.Produto;
import br.com.Loja.model.dto.ProdutoEntradaDto;

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
	        Produto produto = produtoService.associarCategoria(produtoId, categoriaId);
	        return ResponseEntity.ok("Categoria associada ao produto com sucesso.");
	    } catch (ResponseStatusException e) {
	        return ResponseEntity.status(e.getStatusCode()).body(e.getReason() != null ? e.getReason() : "Erro desconhecido");
	    }
	}
	
	@PutMapping("/{produtoId}/remover-categoria")
    public ResponseEntity<String> desassociarCategoria(@PathVariable Long produtoId) {
        try {
            produtoService.desassociarCategoria(produtoId);
            return ResponseEntity.ok("Categoria desassociada do produto com sucesso.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return ResponseEntity.ok(produtos); // Retorna a lista de produtos com status 200
	}

	@DeleteMapping("/{produtoId}")
	public ResponseEntity<String> deletarProduto(@PathVariable Long produtoId) {
	    Produto produto = produtoService.findById(produtoId);
	    if (produto == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
	    }
	    produtoService.deletarProduto(produtoId);
	    return ResponseEntity.ok("Produto removido com sucesso");
	}

}
