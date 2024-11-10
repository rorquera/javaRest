package com.krakedev.inventarios.entidades;

public class EstadoPedido {
	private String codigo;
	private String descripcion;

	public EstadoPedido() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EstadoPedido [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}

}
