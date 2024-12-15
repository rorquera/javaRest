package com.krakedev.pronosticos.entidades;

import java.sql.Timestamp;

public class Partido {
	private int id;
	private Equipo equipoA;
	private Equipo equipoB;
	private Timestamp fecha;

	public Partido() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id + ", equipoA=" + equipoA + ", equipoB=" + equipoB + ", fecha=" + fecha + "]";
	}

}
