package com.krakedev.pronosticos.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.pronosticos.bdd.EquipoBDD;
import com.krakedev.pronosticos.entidades.Equipo;
import com.krakedev.pronosticos.excepciones.KrakeDevException;

@Path("equipo")
public class EquipoService {
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Equipo equipo) {
		System.out.println("Equipo creado: " + equipo);
		try {
			EquipoBDD.crear(equipo);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
