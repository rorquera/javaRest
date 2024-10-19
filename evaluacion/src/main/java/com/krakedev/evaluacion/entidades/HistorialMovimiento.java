package com.krakedev.evaluacion.entidades;

import java.time.LocalDate;

public class HistorialMovimiento {
	private int id;
	private String idProducto;
	private int cantidad;
	private LocalDate fechaMovimiento;

	public HistorialMovimiento() {
	}

	public HistorialMovimiento(int id, String idProducto, int cantidad, LocalDate fechaMovimiento) {
		this.id = id;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.fechaMovimiento = fechaMovimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(LocalDate fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	@Override
	public String toString() {
		return "HistorialMovimiento [id=" + id + ", idProducto=" + idProducto + ", cantidad=" + cantidad
				+ ", fechaMovimiento=" + fechaMovimiento + "]";
	}

}
