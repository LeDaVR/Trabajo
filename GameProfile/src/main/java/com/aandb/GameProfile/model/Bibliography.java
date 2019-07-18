package com.aandb.GameProfile.model;

public class Bibliography {
	int id;
	String nombre;
	String autor;
	String fecha;
	String editorial;
	String edicion;
	
	public Bibliography() {
		this.nombre = "nombre";
		this.autor = "autor";
		this.fecha = "fecha";
		this.editorial = "editorial";
		this.edicion = "edicion";
	}
	
	public Bibliography(String nombre, String autor, String fecha, String editorial, String edicion) {
		this.nombre = nombre;
		this.autor = autor;
		this.fecha = fecha;
		this.editorial = editorial;
		this.edicion = edicion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	
	
}
