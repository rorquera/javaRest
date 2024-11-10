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
		String sql = "SELECT" + "	IDENTIFICADOR," + "	TIPO_DOCUMENTO," + "	NOMBRE," + "	TELEFONO," + "	CORREO,"
				+ "	DIRECCION" + " FROM PUBLIC.PROVEEDORES" + " WHERE UPPER(NOMBRE) LIKE ?";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				String tipoDocumento = rs.getString("tipo_documento");
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
}
