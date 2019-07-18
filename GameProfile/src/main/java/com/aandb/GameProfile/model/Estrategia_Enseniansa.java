package com.aandb.GameProfile.model;

public class Estrategia_Enseniansa {
	int id;
	String tipo;
	
	public Estrategia_Enseniansa() {
		tipo="tipo";
	}
	
	public Estrategia_Enseniansa(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
