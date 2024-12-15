package com.krakedev.pronosticos.entidades;

public class Equipo {
	private String codigo;
	private String nombre;

	public Equipo() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Equipo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
