package com.codingdojo.marcos.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.marcos.modelos.Proyecto;
import com.codingdojo.marcos.modelos.Usuario;
@Repository
public interface RepositorioProyectos extends CrudRepository<Proyecto, Long> {

	//Lista de proyectos que incluyan a un usuario
	List<Proyecto> findByUsuariosUnidosContains(Usuario usuario);
	
	//Lista de proyectos que no incluyan a una persona
	List<Proyecto> findByUsuariosUnidosNotContains(Usuario usuario);

}
