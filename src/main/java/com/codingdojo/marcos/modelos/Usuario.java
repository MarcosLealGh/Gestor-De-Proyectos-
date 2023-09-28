package com.codingdojo.marcos.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //AI
	private Long id;
	
	@NotEmpty(message="El campo de nombre es obligatorio")
	@Size(min=2, message="El nombre debe tener 2 caracteres min.")
	private String nombre;
	
	@NotEmpty(message="El campo de apellido es obligatorio")
	@Size(min=2, message="El apellido debe tener 2 caracteres min.")
	private String apellido;
	
	
	@NotEmpty(message="El campo de email es obligatorio")
	@Email(message="ingresa un correo válido")	
	private String email;
	
	@NotEmpty (message="El campo de password es obligatorio")
	@Size(min=6, message="El password necesita 6 caracteres min.")
	private String password;
	
	@NotEmpty (message="El campo de confirmacion es obligatorio")
	@Size(min=6, message="El campo de confirmacion necesita 6 caracteres min.")
	@Transient //No se guarda en la base de datos.
	private String confirmacion;
	
	@Column(updatable=false) //Este atributo solamente se ingresa una vez, y NUNCA se actualiza
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="lider", fetch=FetchType.LAZY)
	private List<Proyecto> misProyectos; //lista de proyectos
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="proyectos_con_usuarios",
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name="proyecto_id")
			)
	private List<Proyecto> proyectosUnidos;	//Proyectos a los que me uni
	
	public Usuario() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Proyecto> getMisProyectos() {
		return misProyectos;
	}

	public void setMisProyectos(List<Proyecto> misProyectos) {
		this.misProyectos = misProyectos;
	}
	
	public List<Proyecto> getProyectosUnidos() {
		return proyectosUnidos;
	}


	public void setProyectosUnidos(List<Proyecto> proyectosUnidos) {
		this.proyectosUnidos = proyectosUnidos;
	}


	@PrePersist //Antes de hacer la creación
	protected void onCreate() {
		this.createdAt = new Date(); //DEFAULT CURRENT_TIMESTAMP
	}
	
	@PreUpdate //Antes de hacer una actualización
	protected void onUpdate() {
		this.updatedAt = new Date(); //DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	}
	
}
