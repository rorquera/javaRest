package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	public List<Proveedor> buscar(String subcadena) throws KrakeDevException {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;
		TipoDocumento tipoDocumento =new TipoDocumento();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append("	PRO.IDENTIFICADOR,");
		sb.append("	PRO.TIPO_DOCUMENTO,");
		sb.append("	TD.DESCRIPCION,");
		sb.append("	PRO.NOMBRE,");
		sb.append("	PRO.TELEFONO,");
		sb.append("	PRO.CORREO,");
		sb.append("	PRO.DIRECCION");
		sb.append(" FROM");
		sb.append("	PUBLIC.PROVEEDORES PRO");
		sb.append("	INNER JOIN PUBLIC.TIPO_DOCUMENTOS TD ON PRO.TIPO_DOCUMENTO = TD.CODIGO");
		sb.append(" WHERE");
		sb.append("	UPPER(NOMBRE) LIKE ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				tipoDocumento.setCodigo(rs.getString("tipo_documento"));
				tipoDocumento.setDescripcion(rs.getString("descripcion"));
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				proveedor = new Proveedor();
				proveedor.setIdentificador(identificador);
				proveedor.setTipoDocumento(tipoDocumento);
				proveedor.setNombre(nombre);
				proveedor.setTelefono(telefono);
				proveedor.setCorreo(correo);
				proveedor.setDireccion(direccion);
				proveedores.add(proveedor);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return proveedores;
	}

	public List<TipoDocumento> buscarTiposDocumento() throws KrakeDevException {
		List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TipoDocumento tipoDocumento = null;
		String sql = "SELECT codigo, descripcion FROM public.tipo_documentos";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				tipoDocumento = new TipoDocumento();
				tipoDocumento.setCodigo(codigo);
				tipoDocumento.setDescripcion(descripcion);
				tipoDocumentos.add(tipoDocumento);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return tipoDocumentos;
	}
	
	public void crear(Proveedor proveedor) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO public.proveedores(");
		sb.append(" identificador, tipo_documento, nombre, telefono, correo, direccion)");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?)");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, proveedor.getIdentificador());
			ps.setString(2, proveedor.getTipoDocumento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el proveedor");
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
}
