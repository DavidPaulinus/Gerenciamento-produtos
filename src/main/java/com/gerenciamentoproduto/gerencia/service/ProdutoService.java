package com.gerenciamentoproduto.gerencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoproduto.gerencia.model.Produto;
import com.gerenciamentoproduto.gerencia.model.dto.ProdutoDTO;
import com.gerenciamentoproduto.gerencia.service.util.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;

	public Produto cadastrar(ProdutoDTO dto) {
		var _prod = new Produto(dto);
		repo.save(_prod);
		
		return _prod;
	}
	
}
