package com.gerenciamentoproduto.gerencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoproduto.gerencia.model.User;
import com.gerenciamentoproduto.gerencia.model.dto.DadosAutenticacaoDTO;
import com.gerenciamentoproduto.gerencia.model.dto.TokenJwtDTO;
import com.gerenciamentoproduto.gerencia.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenService token;

	@PostMapping
	public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid DadosAutenticacaoDTO dto) {
		var _authenticationToken = new UsernamePasswordAuthenticationToken(dto.nome(), dto.senha());
		var _authentication = manager.authenticate(_authenticationToken);

		var _tokenJWT = token.gerarToken((User) _authentication.getPrincipal());

		return ResponseEntity.ok(new TokenJwtDTO(_tokenJWT));
	}
}
