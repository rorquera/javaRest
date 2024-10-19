package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.Producto;

@Path("productos")
public class ServiciosProducto {
	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertar(Producto producto) {
		System.out.println("Producto insertado: " + producto);
	}

	@Path("actualizar")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizar(Producto producto) {
		System.out.println("Producto actualizado: " + producto);
	}

	@Path("consultar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Producto> consultar() {
		Categoria categoria1 = new Categoria(1, "carbohidrato");
		Categoria categoria2 = new Categoria(2, "proteina");
		Categoria categoria3 = new Categoria(3, "lacteo");
		Producto producto1 = new Producto(1, "papas", categoria1, 2.50, 50);
		Producto producto2 = new Producto(2, "carne", categoria2, 4.50, 20);
		Producto producto3 = new Producto(3, "leche", categoria3, 1.27, 70);
		ArrayList<Producto> productos = new ArrayList<Producto>();
		productos.add(producto1);
		productos.add(producto2);
		productos.add(producto3);
		return productos;
	}
}
