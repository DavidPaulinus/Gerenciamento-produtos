package com.gerenciamentoproduto.gerencia.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import com.gerenciamentoproduto.gerencia.model.Produto;
import com.gerenciamentoproduto.gerencia.model.dto.ProdutoDTO;
import com.gerenciamentoproduto.gerencia.service.util.repository.ProdutoRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class ProdutoServiceTest {
	@MockBean
	private ProdutoRepository repo;
	@Autowired
	private ProdutoService serv;
	
	private ProdutoDTO dto = new ProdutoDTO("Prod",1,"1cm");
	
	@Test
	@DisplayName("Retorna a instancia criada do tipo Produto")
	void cadastrarProduto() {
		var _prod = serv.cadastrar(dto);
		
		assertInstanceOf(Produto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Page<Produto>")
	void listarProduto() {
		var _prod = serv.listar();
		
		assertInstanceOf(Page.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Produto por ID")
	void detalharProduto() {
		var _prod = serv.detalharPorId(1l);
		
		assertInstanceOf(Produto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Produto por ID ao editar")
	void editarProduto() {
		var _prod = serv.editar(1l, dto);
		
		assertInstanceOf(Produto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma String informando que o produtofoi apagado")
	void apagarProduto() {
		var _prod = serv.apagar(1l);
		
		assertInstanceOf(String.class, _prod);
	}
}
