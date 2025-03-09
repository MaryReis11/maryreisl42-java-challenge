package br.com.Loja.model;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

public class Produto {

	private Long id;
	private String nome;
	private Double preco;
	private Categoria categoria; // A relação com a categoria
	private List<Carrinho> carrinhos = new ArrayList<>(); // Lista de carrinhos onde este produto está

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
	
	public List<Carrinho> getCarrinhos() {
        return carrinhos;
    }

    public void adicionarAoCarrinho(Carrinho carrinho) {
        this.carrinhos.add(carrinho);
    }
    
 // Método para verificar se o produto está em algum carrinho
    public boolean estaNoCarrinho() {
        return !carrinhos.isEmpty(); // Retorna true se o produto estiver associado a algum carrinho
    }

    public void removerDoCarrinho(Carrinho carrinho) {
        this.carrinhos.remove(carrinho);
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