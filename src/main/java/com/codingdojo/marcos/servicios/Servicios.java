package com.codingdojo.marcos.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.marcos.modelos.Usuario;
import com.codingdojo.marcos.repositorios.RepositorioUsuarios;

@Service
public class Servicios {

	@Autowired
	private RepositorioUsuarios repoUsuarios;
	
	//Me registra un nuevo usuario
	public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {
		//Comparamos contraseñas.
		String contrasena = nuevoUsuario.getPassword();
		String confirmacion = nuevoUsuario.getConfirmacion();
		if(!contrasena.equals(confirmacion)) {
			result.rejectValue("confirmacion", "Matches", "Las contraseñas no coinciden");
		}
		
		//Revisamos que el correo no exista en mi BD
		String email = nuevoUsuario.getEmail();
		Usuario existeUsuario = repoUsuarios.findByEmail(email);
		if(existeUsuario != null) {
			//El coreo ya se registro
			result.rejectValue("email", "unique", "El correo ingresado ya se encuentra registrado");
		}
		//Si existe error, entonces regresamos null
		if(result.hasErrors()) {
			return null;
			}else {
				//si no hay error Guardamos
				//Encriptamos contraseña
				String contra_encriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
				nuevoUsuario.setPassword(contra_encriptada);
				return repoUsuarios.save(nuevoUsuario);
			}
		
	}
	//Inicia sesion de un usuario
	public Usuario login(String email, String password) {
		//Revisamos que el correo este en mi BD
		Usuario usuarioInicioSesion = repoUsuarios.findByEmail(email);
		if (usuarioInicioSesion == null) {
			return null;
		}
		//Comparar contraseña
		if(BCrypt.checkpw(password, usuarioInicioSesion.getPassword())) {
			return usuarioInicioSesion;
			
		}else {
			return null;
		}
	}
}
