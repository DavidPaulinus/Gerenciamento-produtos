package com.gerenciamentoproduto.gerencia.service.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciamentoproduto.gerencia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
