package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	public Usuario(String nombre,String apellido) {
		super();
		this.nombre=nombre;
		this.apellido=apellido;
	}
	public Usuario(){}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido=apellido;
	}
	
}
