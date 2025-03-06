package br.com.Loja.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Carrinho {

	private Long id;
	private Set<Produto> produtos;

	public Carrinho(Long id) {
		this.id = id;
		this.produtos = new HashSet<>();
	}

	public Carrinho() {
		this.produtos = new HashSet<>();
	}

	// Getter e Setter para id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter e Setter para produtos
	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	// Método para adicionar produto ao carrinho
	public void adicionarProduto(Produto produto) {
		this.produtos.add(produto);
	}

	// Método para remover produto do carrinho
	public void removerProduto(Long produtoId) {
		this.produtos.remove(produtoId);
	}

}