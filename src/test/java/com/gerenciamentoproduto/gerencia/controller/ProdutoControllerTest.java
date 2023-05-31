package com.gerenciamentoproduto.gerencia.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gerenciamentoproduto.gerencia.model.dto.ProdutoDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProdutoControllerTest {
	@Autowired
	private MockMvc moc;
	@Autowired
	private JacksonTester<ProdutoDTO> jack;
	
	private ProdutoDTO dto = new ProdutoDTO("Produto1",2,"12cm²");
	private ProdutoDTO dtoEdi = new ProdutoDTO("Produto1",4,"12cm²");
	
	@Test
	@DisplayName("Retorna 201 ao criar produto")
	void criar() throws Exception {
		var _json = jack.write(dto).getJson();
		
		moc.perform(MockMvcRequestBuilders.post("/produto/cadastrar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	@DisplayName("Retorna 200 ao listar produto")
	void listar() throws Exception {		
		moc.perform(MockMvcRequestBuilders.post("/produto/listar")
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao detalhar produto")
	void detalhar() throws Exception {
		moc.perform(
				MockMvcRequestBuilders.post("/produto/detalhar/{id}", 1)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao editar produto")
	void editar() throws Exception {
		var _json = jack.write(dtoEdi).getJson();
		
		moc.perform(MockMvcRequestBuilders.post("/produto/editar/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	@DisplayName("Retorna 200 ao apagar produto")
	void apagar() throws Exception {
		moc.perform(MockMvcRequestBuilders.post("/produto/apagar/{id}", 1)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
}
