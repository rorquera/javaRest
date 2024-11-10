package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class Producto {
	private int codigo;
	private String nombre;
	private UnidadMedida udm;
	private BigDecimal precioVenta;
	private Boolean tieneIva;
	private BigDecimal coste;
	private Categoria categoria;
	private int stock;

	public Producto() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UnidadMedida getUdm() {
		return udm;
	}

	public void setUdm(UnidadMedida udm) {
		this.udm = udm;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Boolean getTieneIva() {
		return tieneIva;
	}

	public void setTieneIva(Boolean tieneIva) {
		this.tieneIva = tieneIva;
	}

	public BigDecimal getCoste() {
		return coste;
	}

	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", udm=" + udm + ", precioVenta=" + precioVenta
				+ ", tieneIva=" + tieneIva + ", coste=" + coste + ", categoria=" + categoria + ", stock=" + stock + "]";
	}

}
