package com.gerenciamentoproduto.gerencia.service.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.gerenciamentoproduto.gerencia.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByNome(String username);

}
