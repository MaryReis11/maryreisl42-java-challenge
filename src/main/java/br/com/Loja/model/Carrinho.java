package br.com.Loja.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Carrinho {

	private Long id;
	private Set<Produto> produtos;
	private boolean finalizado; // Flag para indicar se o carrinho está finalizado

	public Carrinho(Long id) {
		this.id = id;
		this.produtos = new HashSet<>();
		this.finalizado = false; // Inicialmente, o carrinho não está finalizado
	}
	
	

	public Carrinho() {
		this.id = id;
		this.produtos = new HashSet<>();
		this.finalizado = false; // Inicialmente, o carrinho não está finalizado
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

	// Adicionar um produto ao carrinho
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        produto.adicionarAoCarrinho(this); // Vincula o produto ao carrinho
    }

    // Remover um produto do carrinho
    public boolean removerProduto(Produto produto) {
        return produtos.remove(produto);
    }

    // Finalizar o carrinho, tornando os produtos imutáveis
    public void finalizarCarrinho() {
        this.finalizado = true;
    }

    // Verificar se o carrinho está finalizado
    public boolean isFinalizado() {
        return finalizado;
    }

	

}