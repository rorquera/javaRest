package com.krakedev.inventarios.entidades;

public class Proveedor {
	private String identificador;
	private TipoDocumento tipoDocumento;
	private String nombre;
	private String telefono;
	private String correo;
	private String direccion;

	public Proveedor() {
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Proveedor [identificador=" + identificador + ", tipoDocumento=" + tipoDocumento + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + "]";
	}

}
