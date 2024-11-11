package com.krakedev.inventarios.entidades;

import java.sql.Timestamp;

public class HistorialStock {
	private int codigo;
	private Timestamp fecha;
	private String referencia;
	private Producto producto;
	private int cantidad;

	public HistorialStock() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "HistorialStock [codigo=" + codigo + ", fecha=" + fecha + ", referencia=" + referencia + ", producto="
				+ producto + ", cantidad=" + cantidad + "]";
	}

}
