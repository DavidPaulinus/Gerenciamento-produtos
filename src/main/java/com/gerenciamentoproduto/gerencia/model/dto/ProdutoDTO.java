package com.gerenciamentoproduto.gerencia.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
		@NotBlank
		String nome, 
		@NotNull
		Integer quantidade, 
		@NotBlank
		String tamanho,
		@NotBlank
		String senha) {

}
