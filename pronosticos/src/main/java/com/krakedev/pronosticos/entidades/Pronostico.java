package com.krakedev.pronosticos.entidades;

public class Pronostico {
	private int id;
	private Usuario usuario;
	private Partido partido;
	private Equipo equipoA;
	private Equipo equipoB;
	private int marcadorEqA;
	private int marcadorEqB;

	public Pronostico() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(Equipo equipoA) {
		this.equipoA = equipoA;
	}

	public Equipo getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(Equipo equipoB) {
		this.equipoB = equipoB;
	}

	public int getMarcadorEqA() {
		return marcadorEqA;
	}

	public void setMarcadorEqA(int marcadorEqA) {
		this.marcadorEqA = marcadorEqA;
	}

	public int getMarcadorEqB() {
		return marcadorEqB;
	}

	public void setMarcadorEqB(int marcadorEqB) {
		this.marcadorEqB = marcadorEqB;
	}

	@Override
	public String toString() {
		return "Pronostico [id=" + id + ", usuario=" + usuario + ", partido=" + partido + ", equipoA=" + equipoA
				+ ", equipoB=" + equipoB + ", marcadorEqA=" + marcadorEqA + ", marcadorEqB=" + marcadorEqB + "]";
	}

}
