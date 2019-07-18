package com.aandb.GameProfile.model;

public class Evaluacion {
	private int id;
	private int silabo_id;
	private String tipo;
	private String evaluacion;
	private int peso;
	private String fecha;
	
	public Evaluacion() {
		this.tipo = "tipo";
		this.evaluacion = "evaluacion";
		this.peso = 0;
		this.fecha = "fecha";
	}
	public Evaluacion(int silabo_id, String tipo, String evaluacion, int peso, String fecha) {
		this.silabo_id = silabo_id;
		this.tipo = tipo;
		this.evaluacion = evaluacion;
		this.peso = peso;
		this.fecha = fecha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSilabo_id() {
		return silabo_id;
	}
	public void setSilabo_id(int silabo_id) {
		this.silabo_id = silabo_id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}
