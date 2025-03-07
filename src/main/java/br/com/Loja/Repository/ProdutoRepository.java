package br.com.Loja.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.Loja.model.Categoria;
import br.com.Loja.model.Produto;

@Repository
public class ProdutoRepository {

	private List<Produto> produtos = new ArrayList<>();
	private long idCounter = 1; // Contador de IDs

	// Verifica se o produto já existe (por nome)
	public boolean existsByNome(String nome) {
		return produtos.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(nome));
	}

	// Salva o produto no repositório em memória
    public Produto save(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(idCounter++);
        }
        produtos.add(produto);
        return produto;
    }

	public void delete(Produto produto) {
		produtos.remove(produto);
	}

	// Retorna todos os produtos
	public List<Produto> findAll() {
		return produtos;
	}

	// Retorna um produto pelo ID
	public Produto findById(Long id) {
		return produtos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}
	
}
