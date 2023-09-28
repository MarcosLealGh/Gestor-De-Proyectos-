package com.codingdojo.marcos.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.marcos.modelos.Usuario;

public interface RepositorioUsuarios extends CrudRepository<Usuario, Long>  {

	//SELECT * FROM usuarios WHERE email = EMAIL QUE RECIBIMOS
	Usuario findByEmail(String email);
}
