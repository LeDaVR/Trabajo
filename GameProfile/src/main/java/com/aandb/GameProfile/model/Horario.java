package com.aandb.GameProfile.model;

public class Horario {
	private int id;
	private int id_silabo;
	private String dia_semana;
	private String tipo_clase;
	private String grupo;
	private String horario;
	
	Horario(){
		this.dia_semana = "dia_semana";
		this.tipo_clase = "tipo_clase";
		this.grupo = "grupo";
		this.horario = "horario";
	}

	public Horario(int id_silabo, String dia_semana, String tipo_clase, String grupo, String horario) {
		this.id_silabo = id_silabo;
		this.dia_semana = dia_semana;
		this.tipo_clase = tipo_clase;
		this.grupo = grupo;
		this.horario = horario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_silabo() {
		return id_silabo;
	}

	public void setId_silabo(int id_silabo) {
		this.id_silabo = id_silabo;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public String getTipo_clase() {
		return tipo_clase;
	}

	public void setTipo_clase(String tipo_clase) {
		this.tipo_clase = tipo_clase;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public boolean complete() {
		return horario!="";
	}
	
	
}
