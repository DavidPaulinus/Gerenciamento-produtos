package com.gerenciamentoproduto.gerencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gerenciamentoproduto.gerencia.model.dto.DetalharProdutoDTO;
import com.gerenciamentoproduto.gerencia.model.dto.ProdutoDTO;
import com.gerenciamentoproduto.gerencia.service.ProdutoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService serv;
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<DetalharProdutoDTO> cadastrarProduto(@RequestBody @Valid ProdutoDTO dto, UriComponentsBuilder uri){
		var _prod = serv.cadastrar(dto);
		
		return ResponseEntity.created(uri.path("/produto/cadastrar").buildAndExpand(_prod.getId()).toUri()).body(new DetalharProdutoDTO(_prod));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<Page<DetalharProdutoDTO>> listarProdutos(){
		return ResponseEntity.ok(serv.listar().map(DetalharProdutoDTO::new));
	}
	@GetMapping("/detalhar/{id}")
	public ResponseEntity<DetalharProdutoDTO> detalharProduto(@PathVariable Long id){
		var _prod = serv.detalharPorId(id);
		return ResponseEntity.ok(new DetalharProdutoDTO(_prod));
	}
}
