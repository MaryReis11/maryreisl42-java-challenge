package br.com.Loja.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.Loja.model.Categoria;

@Repository
public class CategoriaRepository {

	private List<Categoria> categorias = new ArrayList<>();
	private long idCounter = 1;

	public Categoria save(Categoria categoria) {
		categoria.setId(idCounter++);
		categorias.add(categoria);
		return categoria;
	}

	public List<Categoria> findAll() {
		return categorias;
	}

	public void delete(Long categoriaId) {
		categorias.removeIf(c -> c.getId().equals(categoriaId));
	}

	public Optional<Categoria> findById(Long categoriaId) {
		return categorias.stream().filter(c -> c.getId().equals(categoriaId)).findFirst();
	}
}
