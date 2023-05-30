package com.gerenciamentoproduto.gerencia.model.dto;

import com.gerenciamentoproduto.gerencia.model.Produto;

public record DetalharProdutoDTO(Long id, String nome, Integer quantidade, String tamanho) {

	public DetalharProdutoDTO(Produto prod) {
		this(prod.getId(), prod.getNome(), prod.getQuantidade(), prod.getTamanho());
	}

}
