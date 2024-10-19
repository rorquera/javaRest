package com.krakedev.inventario.entidades;

public class Producto {
	private int codigo;
	private String nombre;
	private Categoria categoria;
	private double precio;
	private int stock;

	public Producto(int codigo, String nombre, Categoria categoria, double precio, int stock) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio
				+ ", stock=" + stock + "]";
	}

}
