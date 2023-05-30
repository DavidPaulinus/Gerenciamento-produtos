package com.gerenciamentoproduto.gerencia.model;

import com.gerenciamentoproduto.gerencia.model.dto.ProdutoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer quantidade;
	private String tamanho;

	public Produto(ProdutoDTO dto) {
		this.nome = dto.nome();
		this.quantidade = dto.quantidade();
		this.tamanho = dto.tamanho();
	}
}
