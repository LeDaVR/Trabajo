package com.aandb.GameProfile.model;

public class Departamento_Academico {
	int id;
	String name;
	
	public Departamento_Academico() {
		name="nombre";
	}
	
	public Departamento_Academico(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
