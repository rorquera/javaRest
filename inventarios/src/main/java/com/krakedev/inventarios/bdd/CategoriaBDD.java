package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class CategoriaBDD {
	public void crear(Categoria categoria) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" PUBLIC.CATEGORIAS (NOMBRE, CATEGORIA_PADRE)");
		sb.append(" VALUES");
		sb.append(" (?, ?)");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoria.getNombre());
			ps.setInt(2, categoria.getCategoriaPadre().getCodigo());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar la categoria");
		} catch (KrakeDevException e) {
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

	public void actualizar(Categoria categoria) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE PUBLIC.CATEGORIAS");
		sb.append(" SET");
		sb.append(" NOMBRE = ?,");
		sb.append(" CATEGORIA_PADRE = ?");
		sb.append(" WHERE");
		sb.append(" CODIGO = ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoria.getNombre());
			ps.setInt(2, categoria.getCategoriaPadre().getCodigo());
			ps.setInt(3, categoria.getCodigo());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar la categoria");
		} catch (KrakeDevException e) {
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

	public List<Categoria> buscar() throws KrakeDevException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria categoria = null;
		Categoria categoriaPadre = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" CODIGO,");
		sb.append(" NOMBRE,");
		sb.append(" CATEGORIA_PADRE");
		sb.append(" FROM");
		sb.append(" PUBLIC.CATEGORIAS");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("codigo");
				String nombre = rs.getString("nombre");
				categoriaPadre = new Categoria();
				categoriaPadre.setCodigo(rs.getInt("codigo"));
				categoria = new Categoria();
				categoria.setCodigo(codigo);
				categoria.setNombre(nombre);
				categoria.setCategoriaPadre(categoriaPadre);
				categorias.add(categoria);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return categorias;
	}
}
