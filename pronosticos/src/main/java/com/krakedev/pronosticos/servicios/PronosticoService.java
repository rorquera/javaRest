package com.krakedev.pronosticos.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.pronosticos.bdd.PronosticoBDD;
import com.krakedev.pronosticos.entidades.Pronostico;
import com.krakedev.pronosticos.excepciones.KrakeDevException;

@Path("pronostico")
public class PronosticoService {
	@Path("buscar/{identificador}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("identificador") String identificador) {
		PronosticoBDD pronosticoBDD = new PronosticoBDD();
		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		try {
			pronosticos = pronosticoBDD.buscar(identificador);
			return Response.ok(pronosticos).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
