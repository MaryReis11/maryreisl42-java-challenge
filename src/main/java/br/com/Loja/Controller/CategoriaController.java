package br.com.Loja.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.Loja.Service.CategoriaService;
import br.com.Loja.model.Categoria;
import br.com.Loja.model.dto.CategoriaEntradaDto;
import br.com.Loja.model.dto.CategoriaSaidaDto;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;

	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@PostMapping
	public ResponseEntity<CategoriaSaidaDto> cadastrarCategoria(@RequestBody CategoriaEntradaDto categoriaEntradaDto) {
		Categoria categoria = categoriaService.criarCategoria(categoriaEntradaDto);
		CategoriaSaidaDto categoriaSaidaDto = new CategoriaSaidaDto(categoria.getId(), categoria.getNome());
		return new ResponseEntity<>(categoriaSaidaDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaSaidaDto>> listarCategorias() {
		List<Categoria> categorias = categoriaService.listarCategorias();
		List<CategoriaSaidaDto> categoriaSaidaDtos = categorias.stream()
				.map(c -> new CategoriaSaidaDto(c.getId(), c.getNome())).collect(Collectors.toList());
		return ResponseEntity.ok(categoriaSaidaDtos);
	}

	@DeleteMapping("/{categoriaId}")
	public ResponseEntity<String> removerCategoria(@PathVariable Long categoriaId) {
		// Verificar se a categoria está associada a produtos ou carrinhos
		if (!categoriaService.podeRemoverCategoria(categoriaId)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Categoria não pode ser removida porque está associada a produtos ou carrinhos.");
		}
		categoriaService.removerCategoria(categoriaId);
		return ResponseEntity.ok("Categoria removida com sucesso.");
	}

}
