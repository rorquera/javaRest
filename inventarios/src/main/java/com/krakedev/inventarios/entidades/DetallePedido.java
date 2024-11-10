package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetallePedido {
	private int codigo;
	private CabeceraPedido cabeceraPedido;
	private Producto producto;
	private int cantidadSolicitada;
	private BigDecimal subtotal;
	private int cantidadRecibida;

	public DetallePedido() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public CabeceraPedido getCabeceraPedido() {
		return cabeceraPedido;
	}

	public void setCabeceraPedido(CabeceraPedido cabeceraPedido) {
		this.cabeceraPedido = cabeceraPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public int getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	@Override
	public String toString() {
		return "DetallePedido [codigo=" + codigo + ", cabeceraPedido=" + cabeceraPedido + ", producto=" + producto
				+ ", cantidadSolicitada=" + cantidadSolicitada + ", subtotal=" + subtotal + ", cantidadRecibida="
				+ cantidadRecibida + "]";
	}

}
