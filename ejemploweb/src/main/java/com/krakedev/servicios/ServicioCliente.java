package com.krakedev.servicios;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.Cliente;
import com.krakedev.excepciones.KrakeDevException;
import com.krakedev.persistencia.ClientesBDD;

@Path("customers")
public class ServicioCliente {
	@Path("m1")
	@GET
	public String saludar() {
		return "Hola, este es un servicio rest!!!";
	}

	@Path("buscar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente buscar() {
		Cliente cliente = new Cliente("1822245219", "Rolando", 0);
		return cliente;
	}

	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Cliente cliente) {
		System.out.println("Cliente insertado: " + cliente);
		ClientesBDD cli = new ClientesBDD();
		try {
			cli.insertar(cliente);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Cliente cliente) {
		System.out.println("Cliente actualizado: " + cliente);
		ClientesBDD cli = new ClientesBDD();
		try {
			cli.actualizar(cliente);
			return Response.ok().build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerClientes() {
		ClientesBDD cli = new ClientesBDD();
		List<Cliente> clientes = null;
		try {
			clientes = cli.recuperarTodos();
			return Response.ok(clientes).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("buscarPorCedula/{cedulaParam}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorCedula(@PathParam("cedulaParam") String cedula) {
		ClientesBDD cli = new ClientesBDD();
		Cliente cliente = null;
		try {
			cliente = cli.buscarPorPk(cedula);
			return Response.ok(cliente).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("buscarPorHijos/{numHijosParam}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorHijos(@PathParam("numHijosParam") int numeroHijos) {
		ClientesBDD cli = new ClientesBDD();
		List<Cliente> clientes = null;
		try {
			clientes = cli.buscarPorHijos(numeroHijos);
			return Response.ok(clientes).build();
		} catch (KrakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
