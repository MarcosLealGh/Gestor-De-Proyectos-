package com.codingdojo.marcos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.marcos.modelos.Proyecto;
import com.codingdojo.marcos.modelos.Usuario;
import com.codingdojo.marcos.repositorios.RepositorioProyectos;
import com.codingdojo.marcos.repositorios.RepositorioUsuarios;

@Service
public class ServicioProyectos {
	@Autowired
	private RepositorioUsuarios ru;
	
	@Autowired
	private RepositorioProyectos rp;

	//guardar un proyecto
	public Proyecto guardarProyecto(Proyecto nuevoProyecto) {
		return rp.save(nuevoProyecto);
	}
	
	//regresa usuario por id
	public Usuario encontrarUsuario(Long id) {
		return ru.findById(id).orElse(null);
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		return ru.save(usuario);
	}
	
	public List<Proyecto> encontrarMisProyectos(Usuario usuarioEnSesion){
		return rp.findByUsuariosUnidosContains(usuarioEnSesion);
	}
	public List<Proyecto> encontrarOtrosProyectos(Usuario usuarioEnSesion){
		return rp.findByUsuariosUnidosNotContains(usuarioEnSesion);
	}
	
	public Proyecto encontrarProyecto(Long id) {
		return rp.findById(id).orElse(null);
	}
	
	public void unirProyecto(Long usuarioId, Long proyectoId) {
		Usuario miUsuario = encontrarUsuario(usuarioId);
		Proyecto proyectoAUnir = encontrarProyecto(proyectoId);
		
		proyectoAUnir.getUsuariosUnidos().add(miUsuario);
		rp.save(proyectoAUnir);
		/* Tambien sirve:
		 miUsuario.getProyectosUnidos().add(ProyectoAUnir);
		 ru.save(miUsuario) 
		 */
	}
	public void salirProyecto(Long usuarioId, Long proyectoId) {
		Usuario miUsuario = encontrarUsuario(usuarioId);
		Proyecto proyectoAUnir = encontrarProyecto(proyectoId);
		
		proyectoAUnir.getUsuariosUnidos().remove(miUsuario);
		rp.save(proyectoAUnir);
	}
	
}
