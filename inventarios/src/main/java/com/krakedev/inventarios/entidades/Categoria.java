package com.krakedev.inventarios.entidades;

public class Categoria {
	private int codigo;
	private String nombre;
	private Categoria categoriaPadre;

	public Categoria() {
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

	public Categoria getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(Categoria categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}

	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", nombre=" + nombre + ", categoriaPadre=" + categoriaPadre + "]";
	}

}
