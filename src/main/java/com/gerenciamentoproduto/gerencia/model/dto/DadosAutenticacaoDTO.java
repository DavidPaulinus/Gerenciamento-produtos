package com.gerenciamentoproduto.gerencia.model.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDTO(
		@NotBlank
		String nome,
		@NotBlank
		String senha
		) {

}
