package com.aandb.GameProfile.model;

public class Silabus {
	private int id;
	private int id_asignatura;
	private String semestre;
	private String contenido;
	private String tipos_evaluacion;
	private String evaluacion_continua;
	private String evaluacion_periodica;
	private String instrumentos;
	private String requisitos_evaluacion;
	private String periodo_academico;
	private int PORCENTAJE_EP1;
	private int PORCENTAJE_EP2;
	private int PORCENTAJE_EP3;
	private int PORCENTAJE_EC1;
	private int PORCENTAJE_EC2;
	private int PORCENTAJE_EC3;
	private String FECHA_EP1;
	private String FECHA_EP2;
	private String FECHA_EP3;
	
	public Silabus() {
		semestre = "";
		contenido = "";
		tipos_evaluacion = "";
		evaluacion_continua = "";
		evaluacion_periodica = "";
		instrumentos = "";
		requisitos_evaluacion = "";
		periodo_academico = "";
		PORCENTAJE_EP1 = 0;
		PORCENTAJE_EP2 = 0;
		PORCENTAJE_EP3 = 0;
		PORCENTAJE_EC1 = 0;
		PORCENTAJE_EC2 = 0;
		PORCENTAJE_EC3 = 0;
		FECHA_EP1 = "";
		FECHA_EP2 = "";
		FECHA_EP3 = "";
	}
	
	public Silabus(int id_asignatura, String semestre, String contenido, String tipos_evaluacion,
			String evaluacion_continua, String evaluacion_periodica, String instrumentos,
			String requisitos_evaluacion,String periodo_academico) {
		this.id_asignatura = id_asignatura;
		this.semestre = semestre;
		this.contenido = contenido;
		this.tipos_evaluacion = tipos_evaluacion;
		this.evaluacion_continua = evaluacion_continua;
		this.evaluacion_periodica = evaluacion_periodica;
		this.instrumentos = instrumentos;
		this.requisitos_evaluacion = requisitos_evaluacion;
		this.periodo_academico = periodo_academico;
		PORCENTAJE_EP1 = 0;
		PORCENTAJE_EP2 = 0;
		PORCENTAJE_EP3 = 0;
		PORCENTAJE_EC1 = 0;
		PORCENTAJE_EC2 = 0;
		PORCENTAJE_EC3 = 0;
		FECHA_EP1 = "";
		FECHA_EP2 = "";
		FECHA_EP3 = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_asignatura() {
		return id_asignatura;
	}

	public void setId_asignatura(int id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getTipos_evaluacion() {
		return tipos_evaluacion;
	}

	public void setTipos_evaluacion(String tipos_evaluacion) {
		this.tipos_evaluacion = tipos_evaluacion;
	}

	public String getEvaluacion_continua() {
		return evaluacion_continua;
	}

	public void setEvaluacion_continua(String evaluacion_continua) {
		this.evaluacion_continua = evaluacion_continua;
	}

	public String getEvaluacion_periodica() {
		return evaluacion_periodica;
	}

	public void setEvaluacion_periodica(String evaluacion_periodica) {
		this.evaluacion_periodica = evaluacion_periodica;
	}

	public String getInstrumentos() {
		return instrumentos;
	}

	public void setInstrumentos(String instrumentos) {
		this.instrumentos = instrumentos;
	}

	public String getRequisitos_evaluacion() {
		return requisitos_evaluacion;
	}

	public void setRequisitos_evaluacion(String requisitos_evaluacion) {
		this.requisitos_evaluacion = requisitos_evaluacion;
	}

	public String getPeriodo_academico() {
		return periodo_academico;
	}

	public void setPeriodo_academico(String periodo_academico) {
		this.periodo_academico = periodo_academico;
	}

	public int getPORCENTAJE_EP1() {
		return PORCENTAJE_EP1;
	}

	public void setPORCENTAJE_EP1(int pORCENTAJE_EP1) {
		PORCENTAJE_EP1 = pORCENTAJE_EP1;
	}

	public int getPORCENTAJE_EP2() {
		return PORCENTAJE_EP2;
	}

	public void setPORCENTAJE_EP2(int pORCENTAJE_EP2) {
		PORCENTAJE_EP2 = pORCENTAJE_EP2;
	}

	public int getPORCENTAJE_EP3() {
		return PORCENTAJE_EP3;
	}

	public void setPORCENTAJE_EP3(int pORCENTAJE_EP3) {
		PORCENTAJE_EP3 = pORCENTAJE_EP3;
	}

	public int getPORCENTAJE_EC1() {
		return PORCENTAJE_EC1;
	}

	public void setPORCENTAJE_EC1(int pORCENTAJE_EC1) {
		PORCENTAJE_EC1 = pORCENTAJE_EC1;
	}

	public int getPORCENTAJE_EC2() {
		return PORCENTAJE_EC2;
	}

	public void setPORCENTAJE_EC2(int pORCENTAJE_EC2) {
		PORCENTAJE_EC2 = pORCENTAJE_EC2;
	}

	public int getPORCENTAJE_EC3() {
		return PORCENTAJE_EC3;
	}

	public void setPORCENTAJE_EC3(int pORCENTAJE_EC3) {
		PORCENTAJE_EC3 = pORCENTAJE_EC3;
	}

	public String getFECHA_EP1() {
		return FECHA_EP1;
	}

	public void setFECHA_EP1(String fECHA_EP1) {
		FECHA_EP1 = fECHA_EP1;
	}

	public String getFECHA_EP2() {
		return FECHA_EP2;
	}

	public void setFECHA_EP2(String fECHA_EP2) {
		FECHA_EP2 = fECHA_EP2;
	}

	public String getFECHA_EP3() {
		return FECHA_EP3;
	}

	public void setFECHA_EP3(String fECHA_EP3) {
		FECHA_EP3 = fECHA_EP3;
	}

	
	
}
