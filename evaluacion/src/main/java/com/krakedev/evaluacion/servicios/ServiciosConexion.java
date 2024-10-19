package com.krakedev.evaluacion.servicios;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.krakedev.evaluacion.excepciones.KrakeException;
import com.krakedev.evaluacion.utils.ConexionBDD;

@Path("bases")
public class ServiciosConexion {
	@Path("probarConexion")
	@POST
	public Response probarConn() {
		try {
			ConexionBDD.obtenerConexion();
			System.out.println("Conexión exitosa");
			return Response.ok().build();
		} catch (KrakeException e) {
			System.out.println("Se a producido un error: " + e.getMessage());
			return Response.serverError().build();
		}
	}
}
