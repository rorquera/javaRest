package com.krakedev.evaluacion.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.krakedev.evaluacion.entidades.Categoria;
import com.krakedev.evaluacion.excepciones.KrakeException;
import com.krakedev.evaluacion.utils.ConexionBDD;

public class HistorialBDD {
	public void insertar(Categoria categoria) throws KrakeException {
		Connection conn = null;
		String sql = "INSERT INTO public.categorias(id, nombre) VALUES (?,?)";
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoria.getId());
			ps.setString(2, categoria.getNombre());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeException("Error al insertar la categoria");
		} catch (KrakeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void actualizar(Categoria categoria) throws KrakeException {
		Connection conn = null;
		String sql = "UPDATE public.categorias SET nombre=? WHERE id=?";
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoria.getNombre());
			ps.setString(2, categoria.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeException("Error al actualizar la categoria");
		} catch (KrakeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Categoria buscarPorId(String id) throws KrakeException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria cliente = null;
		String sql = "SELECT id, nombre FROM public.categorias WHERE id=?";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				cliente = new Categoria(id, nombre);
			}
		} catch (KrakeException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeException("Error al consultar. Detalle: " + e.getMessage());
		}
		return cliente;
	}

	public List<Categoria> recuperarTodos() throws KrakeException {
		List<Categoria> clientes = new ArrayList<Categoria>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria categoria = null;
		String sql = "SELECT id, nombre FROM public.categorias";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String nombre = rs.getString("nombre");
				categoria = new Categoria(id, nombre);
				clientes.add(categoria);
			}
		} catch (KrakeException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeException("Error al consultar. Detalle: " + e.getMessage());
		}
		return clientes;
	}
}
