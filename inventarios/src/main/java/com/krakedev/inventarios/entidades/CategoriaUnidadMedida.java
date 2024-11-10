package com.krakedev.inventarios.entidades;

public class CategoriaUnidadMedida {
	private String codigo;
	private String nombre;

	public CategoriaUnidadMedida() {
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
		return "CategoriaUnidadMedida [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
