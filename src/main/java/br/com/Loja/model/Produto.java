package br.com.Loja.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Produto {

	private Long id;
	private String nome;
	private Double preco;
	private Categoria categoria; // A relação com a categoria

	// Construtor sem argumentos
	public Produto() {
		// Pode deixar vazio ou inicializar com valores padrão
	}

	// Construtor com argumentos
	public Produto(Long id, String nome, Double preco, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria; // Associa a categoria ao produto
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria; // Agora retorna a categoria associada
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria; // Método para setar a categoria
	}

	public void setDescricao(String descricao) {
		// TODO Auto-generated method stub

	}

}
