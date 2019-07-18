package com.aandb.GameProfile.model;

public class Pregunta {
	private int id;
	private int id_evaluacion;
	private String descripcion;
	private String respuesta;
	
	public Pregunta() {
		descripcion="descripcion";
		respuesta="respuesta";
	}
	
	public Pregunta(int id_evaluacion, String descripcion, String respuesta) {
		this.id_evaluacion = id_evaluacion;
		this.descripcion = descripcion;
		this.respuesta = respuesta;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_evaluacion() {
		return id_evaluacion;
	}
	public void setId_evaluacion(int id_evaluacion) {
		this.id_evaluacion = id_evaluacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
	
}
