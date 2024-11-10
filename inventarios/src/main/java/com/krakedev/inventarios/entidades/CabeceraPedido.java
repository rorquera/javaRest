package com.krakedev.inventarios.entidades;

import java.util.Date;
import java.util.List;

public class CabeceraPedido {
	private int numero;
	private Proveedor proveedor;
	private Date fecha;
	private EstadoPedido estado;
	private List<DetallePedido> detallePedido;

	public CabeceraPedido() {
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}



	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CabeceraPedido [numero=" + numero + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estado="
				+ estado + ", detallePedido=" + detallePedido + "]";
	}



}
