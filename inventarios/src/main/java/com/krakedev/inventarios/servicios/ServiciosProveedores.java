package com.krakedev.inventarios.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakeDevException;

@Path("inventarios")
public class ServiciosProveedores {
	@Path("buscar/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {
		ProveedoresBDD pro = new ProveedoresBDD();
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		try {
			proveedores = pro.buscar(subcadena);
			return Response.ok(proveedores).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("tiposdocumentos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar() {
		ProveedoresBDD pro = new ProveedoresBDD();
		List<TipoDocumento> tiposDocumentos = new ArrayList<TipoDocumento>();
		try {
			tiposDocumentos = pro.buscarTiposDocumento();
			return Response.ok(tiposDocumentos).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Proveedor proveedor) {
		System.out.println("Proveedor creado: " + proveedor);
		ProveedoresBDD pro = new ProveedoresBDD();
		try {
			pro.crear(proveedor);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}