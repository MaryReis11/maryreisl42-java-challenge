package br.com.Loja.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Categoria {

	private Long id;
	@NotBlank
	private String nome;

	private List<Carrinho> carrinhos = new ArrayList<>();

	public List<Carrinho> getCarrinhos() {
	    return carrinhos;
	}
	
	private Set<Produto> produtos = new HashSet<>();

	// Construtor
	public Categoria() {
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	// Adicionar produto à categoria
		public void addProduto(Produto produto) {
			this.produtos.add(produto);
		}

		// Remover produto da categoria
		public void removeProduto(Produto produto) {
			this.produtos.remove(produto);
		}
		// Adicione um método para retornar o número de produtos associados
	    public int getIdProdutos() {
	        if (produtos == null) {
	            return 0;  // Se não houver produtos, retorna 0
	        }
	        return produtos.size();  // Retorna o número de produtos associados
	    }
}
