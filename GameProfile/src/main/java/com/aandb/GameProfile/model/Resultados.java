package com.aandb.GameProfile.model;

public class Resultados {
	private int id;
	private String descripcion;
	
	public Resultados() {
		descripcion="descripcion";
	}
	public Resultados(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
