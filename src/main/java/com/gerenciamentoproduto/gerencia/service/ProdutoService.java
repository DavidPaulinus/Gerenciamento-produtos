package com.gerenciamentoproduto.gerencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.gerenciamentoproduto.gerencia.model.Produto;
import com.gerenciamentoproduto.gerencia.model.dto.ProdutoDTO;
import com.gerenciamentoproduto.gerencia.service.util.repository.ProdutoRepository;

import jakarta.validation.Valid;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;

	public Produto cadastrar(ProdutoDTO dto) {
		var _prod = new Produto(dto);
		repo.save(_prod);
		
		return _prod;
	}

	public Page<Produto> listar() {
		return new PageImpl<Produto>(repo.findAll());
	}

	public Produto detalharPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Id invalido"));
	}

	public Produto editar(Long id, @Valid ProdutoDTO dto) {
		
		var _prod = detalharPorId(id);
		_prod.atualizar(dto);
		
		return _prod;
	}

	public String apagar(Long id) {
		repo.deleteById(id);
		
		return "Produto removido com sucesso";
	}
	
}
