package com.aandb.GameProfile.model;

public class Silabo_Bibliografia {
	private int id;
	private String tipo;
	private int id_silabo;
	private int id_bibliografia;
	
	public Silabo_Bibliografia() {
		tipo="tipo";
	}
	
	public Silabo_Bibliografia(String tipo, int id_silabo, int id_bibliografia) {
		this.tipo = tipo;
		this.id_silabo = id_silabo;
		this.id_bibliografia = id_bibliografia;
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


	public int getId_silabo() {
		return id_silabo;
	}


	public void setId_silabo(int id_silabo) {
		this.id_silabo = id_silabo;
	}


	public int getId_bibliografia() {
		return id_bibliografia;
	}


	public void setId_bibliografia(int id_bibliografia) {
		this.id_bibliografia = id_bibliografia;
	}
	
	
}
