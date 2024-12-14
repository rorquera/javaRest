package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.DocumentoBDD;
import com.krakedev.inventarios.entidades.TipoDocumento;

@Path("documentos")
public class ServicioDocumentos {

	@Path("crear")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(TipoDocumento tipoDocumento) {
		System.out.println("Tipo Documento creado: " + tipoDocumento);
		DocumentoBDD doc = new DocumentoBDD();
		try {
			doc.crear(tipoDocumento);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
