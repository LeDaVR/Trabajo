package com.aandb.GameProfile.model;

public class Cronograma {
	int id;
	int id_silabo;
	String semana;
	String tema_evaluacion;
	int avance;
	
	public Cronograma() {
		semana="semana";
		tema_evaluacion="tema - evaluacion";
		avance=0;
	}
	public Cronograma(int id_silabo, String semana,String tema_evaluacion,int avance) {
		this.id_silabo = id_silabo;
		this.semana = semana;
		this.tema_evaluacion=tema_evaluacion;
		this.avance=avance;
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
	public String getSemana() {
		return semana;
	}
	public void setSemana(String semana) {
		this.semana = semana;
	}
	public String getTema_evaluacion() {
		return tema_evaluacion;
	}
	public void setTema_evaluacion(String tema_evaluacion) {
		this.tema_evaluacion = tema_evaluacion;
	}
	public int getAvance() {
		return avance;
	}
	public void setAvance(int avance) {
		this.avance = avance;
	}
	
	
}
