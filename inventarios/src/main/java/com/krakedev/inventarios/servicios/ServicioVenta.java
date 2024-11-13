package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.VentasBDD;
import com.krakedev.inventarios.entidades.CabeceraVenta;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("ventas")
public class ServicioVenta {
	@Path("guardar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(CabeceraVenta venta) {
		System.out.println("Venta creada: " + venta);
		VentasBDD ventaBdd = new VentasBDD();
		try {
			ventaBdd.crearVenta(venta);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
