package br.com.Loja.model.dto;

import lombok.Data;

@Data
public class CategoriaSaidaDto {
	private Long id;
	private String nome;

	// Construtores
	public CategoriaSaidaDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
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
}