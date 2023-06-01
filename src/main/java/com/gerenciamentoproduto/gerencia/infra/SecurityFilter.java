package com.gerenciamentoproduto.gerencia.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gerenciamentoproduto.gerencia.service.TokenService;
import com.gerenciamentoproduto.gerencia.service.util.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter  extends OncePerRequestFilter{
	@Autowired
	private TokenService token;
	@Autowired
	private UserRepository repo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)throws ServletException, IOException {
		var _JwtToken = recuperarToken(request);

		if (_JwtToken != null) {
			var _subject = token.getSubject(_JwtToken);
			var _usuario = repo.findByNome(_subject);

			var _authentication = new UsernamePasswordAuthenticationToken(_usuario, null, _usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(_authentication);
		}

		filter.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		var _authorizationHeader = request.getHeader("Authorization");
		if (_authorizationHeader != null) {
			return _authorizationHeader.replace("Bearer", "");

		}
		return null;
	}

}
