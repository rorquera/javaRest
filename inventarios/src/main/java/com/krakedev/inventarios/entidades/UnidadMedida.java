package com.krakedev.inventarios.entidades;

public class UnidadMedida {
	private String nombre;
	private String descripcion;
	private CategoriaUnidadMedida categoriaUdm;

	public UnidadMedida() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaUnidadMedida getCategoriaUdm() {
		return categoriaUdm;
	}

	public void setCategoriaUdm(CategoriaUnidadMedida categoriaUdm) {
		this.categoriaUdm = categoriaUdm;
	}

	@Override
	public String toString() {
		return "UnidadMedida [nombre=" + nombre + ", descripcion=" + descripcion + ", categoriaUdm=" + categoriaUdm
				+ "]";
	}

}
