package com.aandb.GameProfile.model;

public class Competencia_Resultados {
	int idcompetencias_resultados;
	int competencias_id;
	int resultados_id;
	int nivel;
	
	public Competencia_Resultados() {
		nivel=0;
	}
	
	public Competencia_Resultados(int competencias_id, int resultados_id, int nivel) {
		this.competencias_id = competencias_id;
		this.resultados_id = resultados_id;
		this.nivel = nivel;
	}

	public int getIdcompetencias_resultados() {
		return idcompetencias_resultados;
	}

	public void setIdcompetencias_resultados(int idcompetencias_resultados) {
		this.idcompetencias_resultados = idcompetencias_resultados;
	}

	public int getCompetencias_id() {
		return competencias_id;
	}

	public void setCompetencias_id(int competencias_id) {
		this.competencias_id = competencias_id;
	}

	public int getResultados_id() {
		return resultados_id;
	}

	public void setResultados_id(int resultados_id) {
		this.resultados_id = resultados_id;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
}
